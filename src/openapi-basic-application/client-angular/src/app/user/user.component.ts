import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { DefaultService } from '../backend/api/default.service';
import { User } from '../backend/model/user';

@Component ({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public editing = false;
  public user!: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private backend: DefaultService) { }

  ngOnInit (): void {
    const idString = this.route.snapshot.paramMap.get ('id');
    if (idString != null) {
      const idNumber = Number.parseInt (idString);
      this.backend.readUser (idNumber).subscribe (user => this.user = user);
    }
  }

  save (): void {
    this.editing = false;
    const idString = this.route.snapshot.paramMap.get ('id');
    if (idString != null) {
      const idNumber = Number.parseInt (idString);
      this.backend.updateUser (idNumber, this.user).subscribe ();
    }
  }

  delete (): void {
    this.editing = false;
    const idString = this.route.snapshot.paramMap.get ('id');
    if (idString != null) {
      const idNumber = Number.parseInt (idString);
      this.backend.deleteUser (idNumber).subscribe (() => this.router.navigate (['/list']));
    }
  }
}
