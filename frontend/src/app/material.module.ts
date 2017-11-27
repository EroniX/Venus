import {
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatTableModule,
  MatToolbarModule,
  MatSlideToggleModule
} from '@angular/material';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatMenuModule} from '@angular/material/menu';
import {NgModule} from "@angular/core";

@NgModule({
  imports: [
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule, 
    MatToolbarModule, 
    MatTableModule, 
    MatSelectModule, 
    MatSlideToggleModule, 
    MatExpansionModule, 
    MatProgressSpinnerModule, 
    MatSnackBarModule, 
    MatMenuModule],

  exports: [
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule, 
    MatToolbarModule, 
    MatTableModule, 
    MatSelectModule, 
    MatSlideToggleModule, 
    MatExpansionModule, 
    MatProgressSpinnerModule, 
    MatSnackBarModule, 
    MatMenuModule],
})
export class MaterialModule {
}
