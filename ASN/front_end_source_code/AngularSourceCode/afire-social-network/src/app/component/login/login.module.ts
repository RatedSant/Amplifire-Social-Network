import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';

import { LoginService } from 'src/app/service/login.service';
import { HttpClientModule } from '@angular/common/http';

import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    LoginComponent    
  ],
  imports: [
    HttpClientModule,
    CommonModule,    
    FormsModule,
    ReactiveFormsModule,      
    MatInputModule,
    MatIconModule ,
    MatButtonModule,     
  ], 
  providers: [ LoginService ],
  exports: [] ,
  schemas: [] 
})
export class LoginModule { }
