import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreatePostComponent } from './create-post.component';

import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card'

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    CreatePostComponent
  ],
  imports: [
    CommonModule,    
    MatInputModule,
    MatIconModule ,
    MatButtonModule, 
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressBarModule
  ],
  schemas: [] 
})
export class CreatePostModule { }
