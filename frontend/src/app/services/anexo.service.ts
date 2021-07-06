import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {HttpClient} from "@angular/common/http";
import {AnexoModel} from "../domain/anexo.model";

@Injectable({
  providedIn: 'root'
})
export class AnexoService {

    private api: string = environment.apiUrl + '/anexo';

    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<AnexoModel[]>(`${this.api}`);
    }

    getById(idAnexo: number) {
        return this.http.get<AnexoModel>(`${this.api}/${idAnexo}`);
    }

    save(anexo) {
        return this.http.post<AnexoModel>(`${this.api}`, anexo);
    }

    saveAll(anexos) {
        console.log('ANEXOS: ', anexos);
        return this.http.post<AnexoModel[]>(`${this.api}/save-all`, anexos);
    }

    remove(idAnexo: number) {
        return this.http.delete(`${this.api}/${idAnexo}`);
    }
}
