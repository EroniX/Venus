import {
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatToolbarModule,
  MatSlideToggleModule,
  MatExpansionModule,
  MatProgressSpinnerModule,
  MatSnackBarModule,
  MatMenuModule,
  MatTabsModule,
  MatTableModule,
} from '@angular/material';
import {NgModule} from "@angular/core";
import {CdkTableModule} from '@angular/cdk/table';

@NgModule({
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
    MatMenuModule,
    MatTabsModule,
    CdkTableModule],
})
export class MaterialModule {
}
