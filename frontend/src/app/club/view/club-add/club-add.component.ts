import { Component, OnInit } from '@angular/core';
import { ClubService } from '../../service/club.service'
import { CommonModule } from '@angular/common'
import { Router } from '@angular/router'
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
@Component({
  selector: 'app-club-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './club-add.component.html',
  styleUrl: './club-add.component.css',
})
export class ClubAddComponent {
  form!: FormGroup;
  constructor(
    private clubService: ClubService,
    private router: Router,
    private readonly fb: FormBuilder
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      country: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.invalid){
      this.form.markAllAsTouched();
      return;
    }
    this.clubService.addClub(this.form.value!).subscribe(() => {
      this.router.navigate(['/clubs']);
    });
  }
}
