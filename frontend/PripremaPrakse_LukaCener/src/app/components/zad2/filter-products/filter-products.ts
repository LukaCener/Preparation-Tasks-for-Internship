import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProductModel } from '../../../models/product';
import { Product as ProductService} from '../../../services/product';

@Component({
  selector: 'app-filter-products',
  imports: [FormsModule, CommonModule],
  templateUrl: './filter-products.html',
  styleUrl: './filter-products.scss',
})
export class FilterProducts {

  searchText = '';
  selectedCategory = 'all';
  minPrice: number | null = null;
  maxPrice: number | null = null;

  sortOption: string = 'none';

  products: ProductModel[] = [];
  categories: string[] = [];

  constructor(private productService: ProductService) {
    this.products = this.productService.getProducts();
    this.categories = ['all', ...new Set(this.products.map(p => p.category))];
  }

  get filteredProducts() {
    let result = this.products.filter(p => {
      const matchesText =
        p.name.toLowerCase().includes(this.searchText.toLowerCase());

      const matchesCategory =
        this.selectedCategory === 'all' || p.category === this.selectedCategory;

      const matchesMinPrice =
        this.minPrice === null || p.price >= this.minPrice;

      const matchesMaxPrice =
        this.maxPrice === null || p.price <= this.maxPrice;

      return (
        matchesText &&
        matchesCategory &&
        matchesMinPrice &&
        matchesMaxPrice
      );
    });

    switch (this.sortOption) {
      case 'name-asc':
        result = result.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case 'name-desc':
        result = result.sort((a, b) => b.name.localeCompare(a.name));
        break;
      case 'price-asc':
        result = result.sort((a, b) => a.price - b.price);
        break;
      case 'price-desc':
        result = result.sort((a, b) => b.price - a.price);
        break;
    }

    return result;
  }
}