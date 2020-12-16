import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListComponent} from './list/list.component';
import { UserComponent} from './user/user.component';

const routes: Routes = [
    { pathMatch: 'full', path: '', redirectTo: 'list' },
    { pathMatch: 'full', path: 'list', component: ListComponent },
    { pathMatch: 'full', path: 'user/:id', component: UserComponent },
];

@NgModule({
    imports: [RouterModule.forRoot (routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
