document.addEventListener('DOMContentLoaded', () => {
    const materialesList = document.getElementById('materialesList');
    const agregarMaterialBtn = document.getElementById('agregarMaterial');
    const remitoForm = document.getElementById('remitoForm');

    function crearCamposMaterial() {
        const materialDiv = document.createElement('div');
        materialDiv.className = 'grid grid-cols-3 gap-4 p-4 border rounded-lg';
        
        materialDiv.innerHTML = `
            <div>
                <label class="block text-sm font-medium text-gray-700">Nombre del Material</label>
                <input type="text" required class="material-nombre mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Cantidad</label>
                <input type="number" required min="1" class="material-cantidad mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Unidad</label>
                <input type="text" required class="material-unidad mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
            </div>
        `;

        const eliminarBtn = document.createElement('button');
        eliminarBtn.textContent = 'Eliminar';
        eliminarBtn.className = 'mt-2 text-red-600 hover:text-red-800';
        eliminarBtn.onclick = () => materialDiv.remove();
        
        materialDiv.appendChild(eliminarBtn);
        materialesList.appendChild(materialDiv);
    }

    agregarMaterialBtn.addEventListener('click', crearCamposMaterial);

    remitoForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const materiales = Array.from(materialesList.children).map(div => ({
            nombre: div.querySelector('.material-nombre').value,
            cantidad: parseInt(div.querySelector('.material-cantidad').value),
            unidad: div.querySelector('.material-unidad').value
        }));

        const datos = {
            numeroRemito: document.getElementById('numeroRemito').value,
            fechaRemito: document.getElementById('fechaRemito').value,
            materiales
        };

        try {
            const response = await fetch('/api/remitos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datos)
            });

            if (!response.ok) {
                throw new Error('Error al guardar el remito');
            }

            alert('Remito guardado exitosamente');
            remitoForm.reset();
            materialesList.innerHTML = '';
        } catch (error) {
            alert('Error: ' + error.message);
        }
    });

    // Agregar un material inicial
    crearCamposMaterial();
});