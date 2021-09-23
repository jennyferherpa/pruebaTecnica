export class EmpleadoDTO{
    empleado_id : any = null;
    primer_apellido : string;
    segundo_apellido : string;
    primer_nombre : string;
    otro_nombre : string;
    pais : {id : number};
    identificacion : {id: number};
    nid : string;
    email : string;
    fecha_ingreso : string;
    area : {id: number};
    estado : Boolean = true;
    updatedAt: string;
    registro: string;
}
