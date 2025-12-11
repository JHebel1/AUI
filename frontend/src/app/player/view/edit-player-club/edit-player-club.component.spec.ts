import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPlayerClubComponent } from './edit-player-club.component';

describe('EditPlayerClubComponent', () => {
  let component: EditPlayerClubComponent;
  let fixture: ComponentFixture<EditPlayerClubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditPlayerClubComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditPlayerClubComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
