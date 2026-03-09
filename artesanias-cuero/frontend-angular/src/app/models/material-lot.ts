export interface MaterialLot {
  id: number;
  materialId: number;
  colorId?: number;  // Opcional (puede ser null)
  lotCode: string;
  qtyInitial: number;
  qtyOnHand: number;
  unitCost: number;
  createdAt?: string;
}