export interface Material {
  id: number;
  name: string;
  category: string;
  baseUnit: string;
  stockMin: number;
  active: boolean;
  createdAt?: string;
}