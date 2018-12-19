import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Router} from '@angular/router';

import {DefaultService} from '../api/api/default.service';
import {User} from '../api/model/user';

@Component({
  selector: 'app-inventory',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  @Input() user: User;

  // TODO: load from database over REST API
  assets: Array<String>;

  constructor(
    private route: ActivatedRoute,
    private api: DefaultService,
    private router: Router,
  ) {}

  ngOnInit() {
    this.getUser();
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.api.readUser(id)
      .subscribe(u => this.user = u);
  }

  save(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.api.updateUser(id, this.user).subscribe();
  }

  delete(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.api.deleteUser(id).subscribe(x => this.router.navigate(['/users']));
  }
}
