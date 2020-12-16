import { Component, OnInit } from '@angular/core';

import { DefaultService } from '../backend/api/default.service';
import { User } from '../backend/model/user';

@Component ({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  users: User [];
  constructor (private backend: DefaultService) { }
  ngOnInit (): void {
    this.backend.readUsers ().subscribe (users => this.users = users);
  }
}
