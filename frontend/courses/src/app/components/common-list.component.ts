import { OnInit, ViewChild, Injectable } from '@angular/core';
import { CommonService } from '../services/common.service';
import Swal from 'sweetalert2';
import { PageEvent, MatPaginator } from '@angular/material/paginator';
import { Generic } from '../models/generic';

@Injectable()
export abstract class CommonListComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  title: string;

  list: E[];
  protected nameModel: string;

  totalRegisters = 0;
  actualPage = 0;
  totalByPage = 5;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  @ViewChild(MatPaginator) protected paginator: MatPaginator;

  constructor(protected service: S) { }

  ngOnInit(): void {
    this.findAllPageable();
  }

  public findAllPageable(): void {
    this.service.findAllPageable(`${this.actualPage}`, `${this.totalByPage}`)
    .subscribe(page => {
      this.list = page.content as E[];
      this.totalRegisters = page.totalElements as number;
      this.paginator._intl.itemsPerPageLabel = 'Registros por página';
      this.paginator._intl.firstPageLabel = 'Primera página';
      this.paginator._intl.previousPageLabel = 'Página anterior';
      this.paginator._intl.nextPageLabel = 'Página siguiente';
      this.paginator._intl.lastPageLabel = 'Última página';
    });
  }

  page(event: PageEvent) {
    this.actualPage = event.pageIndex;
    this.totalByPage = event.pageSize;
    this.findAllPageable();
  }

  public delete(e: E) : void {

    Swal.fire({
      title: 'Advertencia',
      text: `¿Está seguro que desea eliminar a ${e.name}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.value) {
        this.service.delete(e.id).subscribe(() => {
          this.findAllPageable();
          Swal.fire('Eliminar: ', `${this.nameModel} ${e.name} eliminado correctamente`, 'success');
        });
      }
    });
  }

}
