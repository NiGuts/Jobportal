import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators'
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { JobsearchService } from '../jobsearch.service';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

public berufsfelder = ["Tippen um Vorschläge zu laden..."];
public zips =  ["Tippen um Vorschläge zu laden..."];
public laender = [];
public regionen = [];
public einstiegsarten= [];

filteredOptions: Observable<string[]>;
filteredZips: Observable<string[]>;
myControl = new FormControl();
myZipControl = new FormControl();
mySelectControl = new FormControl();

constructor(private jobSearchService : JobsearchService) { }

  ngOnInit() {
    this.getBerufsfelder(); 
    this.getZips();
    this.getLaender();
    this.getEinstiegsarten();
    this.getRegionen();
    this.filteredOptions = this.myControl.valueChanges
    .pipe(
      startWith(''),
      map(value => this.BerufsfeldFilter(value))
    );
    
    this.filteredZips = this.myZipControl.valueChanges
    .pipe(
      startWith(''),
      map(value => this.ZipFilter(value))
    );
    }

  private BerufsfeldFilter(value: string): string[] {
    if (value!=null){
    const filterValue = value.toLowerCase();
    this.getBerufsfelder();
    return this.berufsfelder.filter(option => option.toLowerCase().includes(filterValue));}
  }

  private ZipFilter(value: string): string[] {
    if (value!=null){
    const filterValue = value.toLowerCase();
    this.getZips();
    return this.zips.filter(option => option.toLowerCase().includes(filterValue));}
  }

   getBerufsfelder(){
    this.jobSearchService.getBerufsfelder().subscribe(
      berufsfelder => {this.berufsfelder = berufsfelder;
        }
    );
  }
  getZips(){
    this.jobSearchService.getLocationWithZip().subscribe(
      zips => {this.zips = zips;
        }
    );
  }

  getLaender(){
    this.jobSearchService.getLand().subscribe(
      l => {this.laender = l;
        }
    );
  }

  getRegionen(){
    this.jobSearchService.getRegion().subscribe(
      reg => {this.regionen = reg;
        }
    );
      }

    getEinstiegsarten(){
      this.jobSearchService.geEinstiegsarten().subscribe(
        reg => {this.einstiegsarten = reg;
          }
      );
    }


  reset(){
    this.myControl.reset();
    this.mySelectControl.reset();
    this.myZipControl.reset();

  }
}

