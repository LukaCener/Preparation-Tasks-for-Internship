import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

export interface User {
  id?: number;
  name: string;
  email: string;
  phone?: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'https://jsonplaceholder.typicode.com/users';
  private usersSubject = new BehaviorSubject<User[]>([]);
  users$ = this.usersSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadUsers(); 
  }

  loadUsers(): void {
    console.log('Loading users...');
    this.http.get<User[]>(this.apiUrl).subscribe({
      next: (users) => {
        console.log('Users fetched from API:', users);
        this.usersSubject.next(users);
      },
      error: (err) => {
        console.error('Error loading users:', err);
      }
    });
  }

  addUser(user: User): void {
    const current = this.usersSubject.value;
    const newUser = { ...user, id: current.length + 1 };
    this.usersSubject.next([...current, newUser]);
  }
}