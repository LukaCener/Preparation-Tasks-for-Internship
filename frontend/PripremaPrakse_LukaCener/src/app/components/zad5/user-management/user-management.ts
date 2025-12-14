import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UserService, User } from '../../../services/user';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-management',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './user-management.html',
  styleUrl: './user-management.scss',
})
export class UserManagement implements OnInit {
  users$!: Observable<User[]>; 
  form: any; 

  constructor(
    private fb: FormBuilder,
    private userService: UserService
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['']
    });
  }

  ngOnInit(): void {
    this.userService.loadUsers();
    this.users$ = this.userService.users$; 
  }

  submit(): void {
    if (this.form.invalid) return;
    this.userService.addUser(this.form.value as User);
    this.form.reset();
  }

  get name() { return this.form.controls['name']; }
  get email() { return this.form.controls['email']; }
}