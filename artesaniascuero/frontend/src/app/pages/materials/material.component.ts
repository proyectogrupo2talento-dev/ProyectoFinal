import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

import { Material } from '../../models/material.model';
import { MaterialService } from '../../core/services/material.service';

@Component({
  selector: 'app-material',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './material.component.html',
  styleUrls: ['./material.component.css']
})
export class MaterialComponent implements OnInit {

  materiales: Material[] = [];
  loading: boolean = false;
  mensajeError: string = '';
  mensajeExito: string = '';
  editando: boolean = false;
  materialEditandoId: number | null = null;

  mostrarModal: boolean = false;


  // filtro
  filtroCategoria: string = '';

  constructor(
    private materialService: MaterialService,
    private location: Location,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.cargarMateriales();
  }

  cargarMateriales(): void {

    this.mensajeError = '';
    this.loading = true;
    this.cdr.markForCheck();

    this.materialService.getMaterials().subscribe({

      next: (data: Material[]) => {
        this.materiales = data;
        this.loading = false;
        this.cdr.markForCheck();
      },

      error: (err) => {
        this.mensajeError = err?.error?.message || 'Error al cargar materiales';
        console.error('Error al cargar materiales:', err);
        this.loading = false;
        this.cdr.markForCheck();
      }

    });

  }

  abrirModal(): void {
    this.mensajeError = '';
    this.editando = false;
    this.materialEditandoId = null;
    this.nuevoMaterial = {
      name: '',
      category: '',
      base_unit: '',
      stock_min: 0,
      active: true
    };
    this.mostrarModal = true;
  }

  cerrarModal(): void {
    this.mensajeError = '';
    this.mostrarModal = false;
  }

  // objeto para crear material
  nuevoMaterial: any = {
    name: '',
    category: '',
    base_unit: '',
    stock_min: 0,
    active: true
  };

  guardarMaterial(form: NgForm): void {

    this.mensajeError = '';
    this.mensajeExito = '';

    if (form.invalid) {
      this.mensajeError = 'Completa todos los campos obligatorios';
      return;
    }

    if (Number(this.nuevoMaterial.stock_min) < 0) {
      this.mensajeError = 'El stock minimo no puede ser negativo';
      return;
    }

    console.log("Material enviado:", this.nuevoMaterial);

    const request$ = this.editando && this.materialEditandoId !== null
      ? this.materialService.updateMaterial(this.materialEditandoId, this.nuevoMaterial)
      : this.materialService.createMaterial(this.nuevoMaterial);

    request$.subscribe({

      next: (data) => {

        console.log("Material creado:", data);

        this.cargarMateriales();

        // limpiar formulario
        this.nuevoMaterial = {
          name: '',
          category: '',
          base_unit: '',
          stock_min: 0,
          active: true
        };

        this.mensajeExito = this.editando ? 'Material actualizado correctamente' : 'Material creado correctamente';
        this.cerrarModal();
        this.cdr.markForCheck();

      },

      error: (err) => {
        this.mensajeError = err?.error?.message || 'No se pudo guardar el material';
        console.error("Error guardando material", err);
        this.cdr.markForCheck();
      }

    });

  }

  editarMaterial(material: Material): void {
    this.mensajeError = '';
    this.editando = true;
    this.materialEditandoId = material.id;
    this.nuevoMaterial = {
      name: material.name,
      category: material.category,
      base_unit: material.base_unit,
      stock_min: material.stock_min,
      active: material.active
    };
    this.mostrarModal = true;
  }

  eliminarMaterial(material: Material): void {
    const confirmado = confirm(`¿Eliminar el material ${material.name}?`);
    if (!confirmado) {
      return;
    }

    this.materialService.deleteMaterial(material.id).subscribe({
      next: () => {
        this.mensajeExito = 'Material eliminado correctamente';
        this.cargarMateriales();
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.mensajeError = err?.error?.message || 'No se pudo eliminar el material';
        this.cdr.markForCheck();
      }
    });
  }

  // materiales filtrados por categoría
  get materialesFiltrados(): Material[] {

    if (!this.filtroCategoria) {
      return this.materiales;
    }

    return this.materiales.filter(
      m => m.category === this.filtroCategoria
    );

  }

  volver(): void {
    this.location.back();
  }

}