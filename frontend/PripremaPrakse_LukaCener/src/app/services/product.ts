import { Injectable } from '@angular/core';
import { ProductModel } from '../models/product';

@Injectable({
  providedIn: 'root',
})
export class Product {
  private products: ProductModel[] = [
    { name: 'Laptop', category: 'Electronics', price: 1500 },
    { name: 'Football', category: 'Sports', price: 10 },
    { name: 'Chair', category: 'Furniture', price: 150 },
    { name: 'Shoes', category: 'Sports', price: 30 },
    { name: 'Smartphone', category: 'Electronics', price: 700 },
    { name: 'Lamp', category: 'Furniture', price: 35 }
  ];

  getProducts(): ProductModel[] {
    return this.products;
  }
}