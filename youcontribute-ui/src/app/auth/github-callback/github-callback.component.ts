import { tokenize } from '@angular/compiler/src/ml_parser/lexer';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { first } from 'rxjs';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-github-callback',
  templateUrl: './github-callback.component.html',
  styleUrls: ['./github-callback.component.css']
})
export class GithubCallbackComponent implements OnInit {

  loading =false;


  constructor(private route:ActivatedRoute,private service:AuthService ,private router:Router,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params=> {
      this.getAccessToken(params["code"])
    })
  }

  getAccessToken(code :string)
  { 
    this.service.getGithubAccessToken(code)
    .pipe(first())
    .subscribe(res => {
      this.loading=false;
      localStorage.setItem("token",res.accessToken)
      setTimeout(() => {
        this.router.navigate(['home'])
      }, 2000 )
      this.toastr.success("login succesfully","Success")
      console.log(res.accessToken)

  },
  error => {
      this.loading=false;
      this.toastr.error(error.error.message,"Error")

  })

  }

}
