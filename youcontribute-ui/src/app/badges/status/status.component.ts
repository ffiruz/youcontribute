import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css'],
  inputs:['badgeText:badge-text']
})
export class StatusComponent implements OnInit {
 
badgeTextToClass = new Map ([
  ["ACCEPTED","badge rounded-pill bg-primary"],
  ["REJECTED","badge rounded-pill bg-danger"],
  ["PENDING","badge rounded-pill bg-warning"],
  ["COMPLETED","badge rounded-pill bg-success"],
]);

  badgeClass:any
  badgeText:any

  constructor() { }

  ngOnInit(): void {
    this.badgeClass=this.badgeTextToClass.get(this.badgeText);

  }


}
