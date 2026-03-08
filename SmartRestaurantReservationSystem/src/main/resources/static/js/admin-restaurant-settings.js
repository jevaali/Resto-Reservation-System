document.addEventListener('DOMContentLoaded', function() {
    // Initialize variables
    let currentHall = null;
    let currentCellType = 'EMPTY';
    let currentTableSize = '4x4';
    let tables = [];
    let selectedCell = null;
    let isDragging = false;
    let draggedTable = null;
    let dragOffset = { x: 0, y: 0 };

    // Get restaurant data
    const restaurantId = getRestaurantIdFromUrl();
    loadRestaurantData();

    // Initialize event listeners
    document.getElementById('addHallForm').addEventListener('submit', function(e) {
        e.preventDefault();
        createHall();
    });

    document.getElementById('cellTypeSelector').addEventListener('change', function(e) {
        currentCellType = e.target.value;
        updateTableControls();
    });

    document.getElementById('tableSizeSelector').addEventListener('change', function(e) {
        currentTableSize = e.target.value;
    });

    document.getElementById('addTableBtn').addEventListener('click', addTable);
    document.getElementById('saveHallBtn').addEventListener('click', saveHall);
    document.getElementById('clearHallBtn').addEventListener('click', clearHall);

    function getRestaurantIdFromUrl() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('restaurantId') || 1; // Default to 1 if not in URL
    }

    function loadRestaurantData() {
        fetch(`/api/admin/restaurants/${restaurantId}`)
            .then(response => response.json())
            .then(data => {
                console.log('Restaurant data:', data);
                currentHall = data.halls.length > 0 ? data.halls[0] : null;
                tables = currentHall ? currentHall.tables : [];
                
                if (currentHall) {
                    renderHallGrid();
                }
            })
            .catch(error => {
                console.error('Error loading restaurant data:', error);
                showNotification('Failed to load restaurant data', 'error');
            });
    }

    function createHall() {
        const formData = new FormData(document.getElementById('addHallForm'));
        const hallData = {
            name: formData.get('hallName'),
            width: parseInt(formData.get('hallWidth')),
            height: parseInt(formData.get('hallHeight')),
            restaurantId: restaurantId
        };

        fetch('/api/admin/restaurants/' + restaurantId + '/halls', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(hallData)
        })
        .then(response => response.json())
        .then(data => {
            currentHall = data;
            tables = [];
            renderHallGrid();
            showNotification('Hall created successfully', 'success');
            // Hide add hall section and show grid section
            document.querySelector('.add-hall-section').style.display = 'none';
            document.querySelector('.hall-grid-section').style.display = 'block';
        })
        .catch(error => {
            console.error('Error creating hall:', error);
            showNotification('Failed to create hall', 'error');
        });
    }

    function renderHallGrid() {
        const gridContainer = document.getElementById('hallGrid');
        gridContainer.innerHTML = '';
        gridContainer.style.gridTemplateColumns = `repeat(${currentHall.width}, 30px)`;
        gridContainer.style.gridTemplateRows = `repeat(${currentHall.height}, 30px)`;

        for (let y = 0; y < currentHall.height; y++) {
            for (let x = 0; x < currentHall.width; x++) {
                const cell = document.createElement('div');
                cell.className = 'grid-cell EMPTY';
                cell.dataset.x = x;
                cell.dataset.y = y;

                // Check if this cell is part of any table
                const table = tables.find(t => isCellInTable(x, y, t));
                if (table) {
                    cell.className = 'grid-cell TABLE';
                    cell.dataset.tableId = table.id;
                    cell.innerHTML = `<div class="table-indicator">${getTableCapacity(table.size)}</div>`;
                } else {
                    // Check cell type from database
                    const gridCell = currentHall.gridCells.find(c => c.x === x && c.y === y);
                    if (gridCell && gridCell.type !== 'EMPTY') {
                        cell.className = 'grid-cell ' + gridCell.type;
                    }
                }

                cell.addEventListener('click', () => handleCellClick(cell));
                cell.addEventListener('mousedown', (e) => handleCellMouseDown(e, cell));
                cell.addEventListener('mousemove', (e) => handleCellMouseMove(e, cell));
                cell.addEventListener('mouseup', () => handleCellMouseUp());
                cell.addEventListener('mouseleave', () => handleCellMouseUp());

                gridContainer.appendChild(cell);
            }
        }
    }

    function updateTableControls() {
        const tableControls = document.querySelector('.table-controls');
        if (currentCellType === 'TABLE') {
            tableControls.style.display = 'block';
        } else {
            tableControls.style.display = 'none';
        }
    }

    function handleCellClick(cell) {
        if (currentCellType === 'TABLE') {
            if (cell.classList.contains('TABLE')) {
                // Clicking on existing table - select it
                selectedCell = cell;
                document.querySelectorAll('.grid-cell.selected').forEach(c => c.classList.remove('selected'));
                const tableId = cell.dataset.tableId;
                document.querySelectorAll(`[data-tableId="${tableId}"]`).forEach(c => c.classList.add('selected'));
            } else {
                // Clicking on empty cell - place new table
                placeTableAt(parseInt(cell.dataset.x), parseInt(cell.dataset.y));
            }
        } else {
            // For other cell types, just change the cell type
            if (!cell.classList.contains('TABLE')) {
                cell.className = 'grid-cell ' + currentCellType;
            }
        }
    }

    function handleCellMouseDown(e, cell) {
        if (cell.classList.contains('TABLE')) {
            isDragging = true;
            draggedTable = {
                id: parseInt(cell.dataset.tableId),
                x: parseInt(cell.dataset.x),
                y: parseInt(cell.dataset.y),
                size: getTableSize(parseInt(cell.dataset.tableId))
            };
            dragOffset = {
                x: e.clientX - cell.getBoundingClientRect().left,
                y: e.clientY - cell.getBoundingClientRect().top
            };
            cell.classList.add('dragging');
        }
    }

    function handleCellMouseMove(e, cell) {
        if (isDragging && draggedTable) {
            // Calculate new position based on mouse location
            const rect = document.getElementById('hallGrid').getBoundingClientRect();
            const gridX = Math.floor((e.clientX - rect.left) / 30);
            const gridY = Math.floor((e.clientY - rect.top) / 30);
            
            if (gridX !== draggedTable.x || gridY !== draggedTable.y) {
                moveTable(draggedTable.id, gridX, gridY);
                draggedTable.x = gridX;
                draggedTable.y = gridY;
            }
        }
    }

    function handleCellMouseUp() {
        if (isDragging) {
            isDragging = false;
            draggedTable = null;
            document.querySelectorAll('.grid-cell.dragging').forEach(cell => {
                cell.classList.remove('dragging');
            });
        }
    }

    function placeTableAt(x, y) {
        if (!tableFitsAt(x, y)) {
            showNotification('Table does not fit at this position', 'error');
            return;
        }

        const tableData = {
            restaurantId: restaurantId,
            hallId: currentHall.id,
            x: x,
            y: y,
            size: currentTableSize
        };

        fetch(`/api/admin/restaurants/${restaurantId}/tables`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tableData)
        })
        .then(response => response.json())
        .then(table => {
            tables.push(table);
            markTableCells(x, y, currentTableSize, table.id);
            showNotification('Table added successfully', 'success');
        })
        .catch(error => {
            console.error('Error placing table:', error);
            showNotification('Failed to place table', 'error');
        });
    }

    function moveTable(tableId, newX, newY) {
        const table = tables.find(t => t.id === tableId);
        if (!table) return;

        const [width, height] = table.size.split('x').map(Number);
        
        if (!tableFitsAt(newX, newY)) {
            return;
        }

        clearTableCells(tableId);
        
        fetch(`/api/admin/tables/${tableId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                x: newX,
                y: newY
            })
        })
        .then(response => response.json())
        .then(updatedTable => {
            table.x = newX;
            table.y = newY;
            markTableCells(newX, newY, table.size, tableId);
        })
        .catch(error => {
            console.error('Error moving table:', error);
            markTableCells(table.x, table.y, table.size, tableId);
            showNotification('Failed to move table', 'error');
        });
    }

    function addTable() {
        const emptyCell = document.querySelector('.grid-cell.EMPTY:not(.has-table)');
        if (emptyCell) {
            placeTableAt(parseInt(emptyCell.dataset.x), parseInt(emptyCell.dataset.y));
        } else {
            showNotification('No empty cells available for table placement', 'error');
        }
    }

    function saveHall() {
        // Get all cell data
        const gridCells = Array.from(document.querySelectorAll('.grid-cell'));
        const cellData = gridCells.map(cell => ({
            x: parseInt(cell.dataset.x),
            y: parseInt(cell.dataset.y),
            type: cell.className.split(' ')[1],
            tableId: cell.dataset.tableId || null
        }));

        fetch(`/api/admin/halls/${currentHall.id}/grid`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cellData)
        })
        .then(response => response.json())
        .then(() => {
            showNotification('Hall configuration saved successfully', 'success');
        })
        .catch(error => {
            console.error('Error saving hall:', error);
            showNotification('Failed to save hall configuration', 'error');
        });
    }

    function clearHall() {
        if (confirm('Are you sure you want to clear all cells and tables from the hall?')) {
            // Clear all cells and tables
            tables = [];
            renderHallGrid();
            showNotification('Hall cleared successfully', 'success');
        }
    }

    function tableFitsAt(x, y) {
        const [width, height] = currentTableSize.split('x').map(Number);
        const gridWidth = currentHall.width;
        const gridHeight = currentHall.height;
        
        if (x + width > gridWidth || y + height > gridHeight) {
            return false;
        }
        
        for (let dy = 0; dy < height; dy++) {
            for (let dx = 0; dx < width; dx++) {
                const cell = document.querySelector(`.grid-cell[data-x="${parseInt(x) + dx}"][data-y="${parseInt(y) + dy}"]`);
                if (!cell || cell.classList.contains('BLOCKED') || (cell.classList.contains('TABLE') && cell.dataset.tableId !== draggedTable?.id)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    function isCellInTable(x, y, table) {
        const [width, height] = table.size.split('x').map(Number);
        return x >= table.x && x < table.x + width && y >= table.y && y < table.y + height;
    }

    function markTableCells(x, y, size, tableId) {
        const [width, height] = size.split('x').map(Number);
        for (let dy = 0; dy < height; dy++) {
            for (let dx = 0; dx < width; dx++) {
                const cell = document.querySelector(`.grid-cell[data-x="${parseInt(x) + dx}"][data-y="${parseInt(y) + dy}"]`);
                if (cell) {
                    cell.className = 'grid-cell TABLE';
                    cell.dataset.tableId = tableId;
                    cell.innerHTML = `<div class="table-indicator">${getTableCapacity(size)}</div>`;
                }
            }
        }
    }

    function clearTableCells(tableId) {
        document.querySelectorAll(`.grid-cell[data-tableId="${tableId}"]`).forEach(cell => {
            cell.className = 'grid-cell EMPTY';
            delete cell.dataset.tableId;
            cell.innerHTML = '';
        });
    }

    function getTableSize(tableId) {
        const table = tables.find(t => t.id === tableId);
        return table ? table.size.split('x').map(Number) : [4, 4];
    }

    function getTableCapacity(size) {
        const [width, height] = size.split('x').map(Number);
        const area = width * height;
        if (area === 16) return '2-4'; // 4x4
        if (area === 24) return '4-6'; // 4x6
        if (area === 32) return '6-8'; // 4x8
        return '?';
    }

    function showNotification(message, type) {
        // Create notification element
        const notification = document.createElement('div');
        notification.className = `notification ${type}`;
        notification.textContent = message;
        
        // Add to DOM
        document.body.appendChild(notification);
        
        // Remove after 3 seconds
        setTimeout(() => {
            notification.style.opacity = '0';
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    }

    // Show grid section if restaurant already has a hall
    if (currentHall) {
        document.querySelector('.hall-grid-section').style.display = 'block';
        document.querySelector('.add-hall-section').style.display = 'none';
    } else {
        document.querySelector('.hall-grid-section').style.display = 'none';
        document.querySelector('.add-hall-section').style.display = 'block';
    }
});
