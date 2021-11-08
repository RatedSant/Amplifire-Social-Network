import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {MatListModule} from '@angular/material/list';

import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [    
    
  ],
  imports: [
    CommonModule,
    MatListModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatInputModule,
    ReactiveFormsModule, MatIconModule ,
    RouterModule  
  ],
  schemas: [] 
})
export class NewsFeedModule { }
