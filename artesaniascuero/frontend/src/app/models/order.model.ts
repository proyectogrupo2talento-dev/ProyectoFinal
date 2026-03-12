import { Material } from './material.model';

export interface Order {
    id?: number;
    order_number: string;
    customer_id: number;
    material: Material | { id: number };
    quantity: number;
    status: string;
    promised_date: string;
    notes: string;
}