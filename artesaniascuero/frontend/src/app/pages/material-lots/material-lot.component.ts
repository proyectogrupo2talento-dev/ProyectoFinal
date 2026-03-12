import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { MaterialLot } from '../../models/material-lot.model';
import { MaterialLotService } from '../../core/services/material-lot.service';
import { Material } from '../../models/material.model';
import { MaterialService } from '../../core/services/material.service';

@Component({
    selector: 'app-material-lot',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './material-lot.component.html',
    styleUrls: ['./material-lot.component.css']
})
export class MaterialLotComponent implements OnInit {

    materialLots: MaterialLot[] = [];
    materials: Material[] = [];
    loading: boolean = false;
    mensajeError: string = '';
    mensajeExito: string = '';
    filtroEstado: string = '';
    mostrarModal: boolean = false;
    editando: boolean = false;
    loteEditandoId: number | null = null;

    nuevoLotMaterial: any = {
        materialId: '',
        color: 0,
        qtyOnHand: 0,
        cost: 0
    };

    constructor(
        private materialLotService: MaterialLotService,
        private materialService: MaterialService,
        private location: Location,
        private cdr: ChangeDetectorRef
    ) { }

    ngOnInit(): void {
        this.cargarMateriales();
        this.cargarMaterialLots();
    }

    cargarMateriales(): void {
        this.materialService.getMaterials().subscribe({
            next: (data: Material[]) => {
                this.materials = data;
                this.cdr.markForCheck();
            },
            error: () => {
                this.mensajeError = 'Error al cargar materiales';
                this.cdr.markForCheck();
            }
        });
    }

    cargarMaterialLots(): void {

        this.loading = true;
        this.cdr.markForCheck();
        this.materialLotService.getMaterialLots().subscribe({

            next: (data: MaterialLot[]) => {
                this.materialLots = data;
                this.loading = false;
                this.cdr.markForCheck();
            },

            error: (err) => {
                this.mensajeError = err?.error?.message || 'Error al cargar lotes de materiales';
                this.loading = false;
                this.cdr.markForCheck();
            }

        });

    }

    abrirModal(): void {
        this.mensajeError = '';
        this.editando = false;
        this.loteEditandoId = null;
        this.nuevoLotMaterial = {
            materialId: '',
            color: 0,
            qtyOnHand: 0,
            cost: 0
        };
        this.mostrarModal = true;
    }

    cerrarModal(): void {
        this.mensajeError = '';
        this.mostrarModal = false;
    }

    guardarMaterialLot(form: NgForm): void {
        this.mensajeError = '';
        this.mensajeExito = '';

        if (form.invalid) {
            this.mensajeError = 'Completa todos los campos obligatorios';
            return;
        }

        if (Number(this.nuevoLotMaterial.qtyOnHand) < 0 || Number(this.nuevoLotMaterial.cost) < 0) {
            this.mensajeError = 'La cantidad y el costo no pueden ser negativos';
            return;
        }

        const payload = {
            materialId: Number(this.nuevoLotMaterial.materialId),
            color: Number(this.nuevoLotMaterial.color),
            qtyOnHand: Number(this.nuevoLotMaterial.qtyOnHand),
            cost: Number(this.nuevoLotMaterial.cost)
        };

        const request$ = this.editando && this.loteEditandoId !== null
            ? this.materialLotService.updateMaterialLot(this.loteEditandoId, payload)
            : this.materialLotService.createMaterialLot(payload);

        request$.subscribe({
            next: () => {
                this.cargarMaterialLots();
                this.nuevoLotMaterial = {
                    materialId: '',
                    color: 0,
                    qtyOnHand: 0,
                    cost: 0
                };
                this.mensajeExito = this.editando
                    ? 'Lote de inventario actualizado correctamente'
                    : 'Lote de inventario creado correctamente';
                this.cerrarModal();
                this.cdr.markForCheck();
            },
            error: (err) => {
                this.mensajeError = err?.error?.message || 'No se pudo crear el lote de inventario';
                this.cdr.markForCheck();
            }
        });
    }

    editarMaterialLot(lot: MaterialLot): void {
        this.mensajeError = '';
        this.editando = true;
        this.loteEditandoId = lot.id;
        this.nuevoLotMaterial = {
            materialId: lot.material.id,
            color: lot.color,
            qtyOnHand: lot.qtyOnHand,
            cost: lot.cost
        };
        this.mostrarModal = true;
    }

    eliminarMaterialLot(lot: MaterialLot): void {
        const confirmado = confirm(`¿Eliminar el lote ${lot.id} de ${lot.material.name}?`);
        if (!confirmado) {
            return;
        }

        this.materialLotService.deleteMaterialLot(lot.id).subscribe({
            next: () => {
                this.mensajeExito = 'Lote eliminado correctamente';
                this.cargarMaterialLots();
                this.cdr.markForCheck();
            },
            error: (err) => {
                this.mensajeError = err?.error?.message || 'No se pudo eliminar el lote';
                this.cdr.markForCheck();
            }
        });
    }

    esStockBajo(lot: MaterialLot): boolean {
        return lot.qtyOnHand <= lot.material.stock_min;
    }

    get materialLotsFiltrados(): MaterialLot[] {
        if (!this.filtroEstado) {
            return this.materialLots;
        }

        if (this.filtroEstado === 'stock-bajo') {
            return this.materialLots.filter((lot) => this.esStockBajo(lot));
        }

        return this.materialLots.filter((lot) => !this.esStockBajo(lot));
    }

    volver(): void {
        this.location.back();
    }
}