import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPlayerClubComponent } from './add-player-club.component';

describe('AddPlayerClubComponent', () => {
  let component: AddPlayerClubComponent;
  let fixture: ComponentFixture<AddPlayerClubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPlayerClubComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPlayerClubComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
