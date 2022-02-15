import { AstMemoryEfficientTransformer } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { first, Observable } from 'rxjs';
import { RepositoryService } from '../services/repository.service';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.css']
})
export class ImportComponent implements OnInit {

  faPlus=faPlus;

  loading=false;

  importRepositoryForm:FormGroup = this.formBuilder.group({
    organization:['',Validators.required],
    repository :['',Validators.required],
  });

  constructor(private formBuilder:FormBuilder,private repositoryService:RepositoryService,private toastr:ToastrService) { }

  ngOnInit(): void {}
  import()
  {
    this.loading=true;
    this.repositoryService.import(this.importRepositoryForm.get('organization')?.value, this.importRepositoryForm.get('repository')?.value)
    .pipe(first()) 
    .subscribe(res => {
      this.loading=false;
      this.toastr.success("imported succesfull","Success")
  },
  error => {
      this.loading=false;
      this.toastr.error(error.error.message,"Error")

  }
    )

}
}
