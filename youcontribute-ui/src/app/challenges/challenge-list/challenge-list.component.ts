import { Component, OnInit } from '@angular/core';
import { Challenge } from 'src/app/_models/challenge';
import { ChallengeService } from 'src/app/_services/challenge.service';

@Component({
  selector: 'app-challenge-list',
  templateUrl: './challenge-list.component.html',
  styleUrls: ['./challenge-list.component.css']
})
export class ChallengeListComponent implements OnInit {

  challenges:Challenge[]= [];
  loading=false;
  constructor(private  challengeService:ChallengeService) {

   }

  ngOnInit(): void {
    this.list();
  }

  list()
  {
    this.loading=true;
    this.challengeService.list()
    .subscribe(response => {
      this.loading=false;
        this.challenges=response;
    },error=> {
      this.loading=false;
    });
  
  }

}
