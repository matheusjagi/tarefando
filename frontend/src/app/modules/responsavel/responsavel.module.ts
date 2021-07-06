import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ResponsavelComponent} from "./responsavel-form/responsavel.component";
import {SharedModule} from "../../shared/shared.module";
import {ResponsavelRoutingModule} from "./responsavel-routing.module";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [ResponsavelComponent],
  imports: [
      CommonModule,
      SharedModule,
      ResponsavelRoutingModule,
      ReactiveFormsModule
  ]
})
export class ResponsavelModule { }
