import { Component, OnInit } from '@angular/core';
import {PlayerService} from '../../service/player.service';
import {ClubService} from '../../../club/service/club.service'
import {FormGroup, FormBuilder, Validators, ReactiveFormsModule} from '@angular/forms'
import {Router, ActivatedRoute} from '@angular/router'
import {CommonModule} from '@angular/common'
import {ChangeDetectorRef} from '@angular/core'

@Component({
  selector: 'app-add-player-club',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-player-club.component.html',
  styleUrl: './add-player-club.component.css',
})
export class AddPlayerClubComponent implements OnInit{
  form!: FormGroup;
  clubName: string | undefined

  clubId: string | undefined
  constructor(
    private playerService: PlayerService,
    private router: Router,
    private route: ActivatedRoute,
    private readonly fb: FormBuilder,
    private clubService: ClubService,
    private cdr: ChangeDetectorRef
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      age: [0, Validators.required],
    });
  }


  ngOnInit(){
    this.route.params.subscribe(params => {
      const id = params['uuid']

      this.clubService.getClub(id).subscribe(club => {
        this.clubName = club.name;
        this.clubId = id;
        this.cdr.detectChanges();
      });
    });

  }

  onSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.playerService.addClubPlayer(this.form.value!, this.clubId!).subscribe(() => {
      this.router.navigate([`/clubs/${this.clubId}`]);
    });
  }
}
