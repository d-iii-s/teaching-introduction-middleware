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
  public user: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private backend: DefaultService) { }

  ngOnInit (): void {
    const id = Number.parseInt (this.route.snapshot.paramMap.get ('id'));
    this.backend.readUser (id).subscribe (user => this.user = user);
  }

  save (): void {
    this.editing = false;
    const id = Number.parseInt (this.route.snapshot.paramMap.get ('id'));
    this.backend.updateUser (id, this.user).subscribe ();
  }

  delete (): void {
    this.editing = false;
    const id = Number.parseInt (this.route.snapshot.paramMap.get ('id'));
    this.backend.deleteUser (id).subscribe (() => this.router.navigate (['/']));
  }
}
