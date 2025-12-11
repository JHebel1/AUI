import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MainComponent } from './ui/component/main/main.component'
import { NavComponent } from './ui/component/nav/nav.component'
import {FooterComponent} from './ui/component/footer/footer.component'
import {HeaderComponent} from './ui/component/header/header.component'
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MainComponent, NavComponent, FooterComponent, HeaderComponent],
  templateUrl: './app.html',
  standalone: true,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
