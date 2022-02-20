import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../_services/repository.service';
import { ToastrService } from 'ngx-toastr';
import { first, Observable } from 'rxjs';
import { Repository } from '../_models/repository';
import { faBug } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  repositories : Repository[] =[] ;
  loading=false ;
  faBug=faBug;

  constructor(private repositoryService:RepositoryService ,private toastr :ToastrService) { }

  ngOnInit(): void {

    this.list()
  }

  list() {
    this.repositoryService.list()
    .pipe(first())
    .subscribe(res => {
      this.loading=false;
      this.repositories=res;
      this.toastr.success("","Success")
  },
  error => {
      this.loading=false;
      this.toastr.error(error.error.message,"Error")
  })
  };
  

}
