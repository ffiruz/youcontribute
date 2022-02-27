import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Issue } from 'src/app/_models/issue';
import { IssueService } from 'src/app/_services/issue.service';
import { faLink } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-issue-list',
  templateUrl: './issue-list.component.html',
  styleUrls: ['./issue-list.component.css']
})
export class IssueListComponent implements OnInit {

  issues:Issue[]= [];
  loading=false;
  faLink=faLink;
  constructor(private  issueService:IssueService ,private toastr:ToastrService,private route:ActivatedRoute) {

   }

/*
ngOnInit->Sayfa render edildiğinde ilk çalışacak yer.
  endpointdeki id yi alacak.--> this.route.params.subscribe(params => {
      this.list(params["id"]);
    })
  }
*/

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.list(params["id"]);
    })
  }

  list(repositoryId:number)
  {
    this.loading=true;
    this.issueService.list(repositoryId)
    .subscribe(response => {
      this.loading=false;
        this.issues=response;
    },error=> {
      this.loading=false;
      this.toastr.error(error.error.message,"Error")
    });
  
  }

}