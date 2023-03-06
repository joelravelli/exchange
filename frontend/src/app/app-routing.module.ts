import { MenuComponent } from './components/menu/menu.component';
import { ContentComponent } from './components/content/content.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RateComponent } from './components/rate/rate.component';
import { GraphComponent } from './components/graph/graph.component';

const routes: Routes = [
  {
    path: '',
    component: MenuComponent,
    children: [
      {
        path: 'graph',
        component: GraphComponent
        
      },
      {
        path: 'rate',
        component: RateComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
