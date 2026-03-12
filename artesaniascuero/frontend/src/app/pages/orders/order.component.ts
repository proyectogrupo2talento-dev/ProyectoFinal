import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

import { Order } from '../../models/order.model';
import { Material } from '../../models/material.model';
import { OrderService } from '../../core/services/order.service';
import { MaterialService } from '../../core/services/material.service';

@Component({
	selector: 'app-order',
	standalone: true,
	imports: [CommonModule, FormsModule],
	templateUrl: './order.component.html',
	styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

	orders: Order[] = [];
	materials: Material[] = [];
	loading: boolean = false;
	mensajeError: string = '';
	mensajeExito: string = '';
	mostrarModal: boolean = false;
	filtroEstado: string = '';

	nuevoPedido: any = {
		order_number: '',
		customer_id: 0,
		material_id: '',
		quantity: 0,
		status: 'PENDIENTE',
		promised_date: '',
		notes: ''
	};

	constructor(
		private orderService: OrderService,
		private materialService: MaterialService,
		private location: Location,
		private cdr: ChangeDetectorRef
	) {}

	ngOnInit(): void {
		this.cargarMateriales();
		this.cargarPedidos();
	}

	cargarPedidos(): void {
		this.loading = true;
		this.cdr.markForCheck();

		this.orderService.getOrders().subscribe({
			next: (data: Order[]) => {
				this.orders = data;
				this.loading = false;
				this.cdr.markForCheck();
			},
			error: (err) => {
				this.mensajeError = err?.error?.message || 'Error al cargar pedidos';
				this.loading = false;
				this.cdr.markForCheck();
			}
		});
	}

	cargarMateriales(): void {
		this.materialService.getMaterials().subscribe({
			next: (data: Material[]) => {
				this.materials = data;
				this.cdr.markForCheck();
			},
			error: () => {
				this.mensajeError = 'No fue posible cargar materiales';
				this.cdr.markForCheck();
			}
		});
	}

	abrirModal(): void {
		this.mensajeError = '';
		this.mostrarModal = true;
	}

	cerrarModal(): void {
		this.mensajeError = '';
		this.mostrarModal = false;
	}

	guardarPedido(form: NgForm): void {
		this.mensajeError = '';
		this.mensajeExito = '';

		if (form.invalid) {
			this.mensajeError = 'Completa todos los campos obligatorios';
			return;
		}

		if (Number(this.nuevoPedido.quantity) <= 0) {
			this.mensajeError = 'La cantidad debe ser mayor a cero';
			return;
		}

		const payload: Order = {
			order_number: this.nuevoPedido.order_number,
			customer_id: Number(this.nuevoPedido.customer_id),
			material: { id: Number(this.nuevoPedido.material_id) },
			quantity: Number(this.nuevoPedido.quantity),
			status: this.nuevoPedido.status,
			promised_date: this.nuevoPedido.promised_date,
			notes: this.nuevoPedido.notes
		};

		this.orderService.createOrder(payload).subscribe({
			next: () => {
				this.cargarPedidos();
				this.nuevoPedido = {
					order_number: '',
					customer_id: 0,
					material_id: '',
					quantity: 0,
					status: 'PENDIENTE',
					promised_date: '',
					notes: ''
				};
				this.mensajeExito = 'Pedido creado y stock actualizado correctamente';
				this.cerrarModal();
				this.cdr.markForCheck();
			},
			error: (err) => {
				this.mensajeError = err?.error?.message || 'No se pudo crear el pedido';
				this.cdr.markForCheck();
			}
		});
	}

	get ordersFiltradas(): Order[] {
		if (!this.filtroEstado) {
			return this.orders;
		}

		return this.orders.filter(order => order.status === this.filtroEstado);
	}

	materialNombre(order: Order): string {
		const material = order.material as Material;
		return material?.name || 'Material no disponible';
	}

	volver(): void {
		this.location.back();
	}
}
