import { Material } from './material.model';

export interface MaterialLot {
    id: number;
    material: Material;
    color: number;
    qtyOnHand: number;
    cost: number;
}