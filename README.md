# imputador-horas

## Tipos imputaciones
* tipo normal: selcciona un rango de fechas para imputar (necesita rangos de fecha)
* tipo diaria: imputa el dia actual (no necesita rangos de fecha)
* tipo semanal: imputa desde el lunes de la semana hasta el dia actual (no necesita rangos de fecha)
* tipo semanal: imputa desde el primer dia del mes hasta el dia actual (no necesita rangos de fecha)

## Formato fechas
Las fechas deberán ir en formato americano con separador '-'. Y con los digitos faltantes a 0.
Ejemplo: 2019-01-01

## Formato horas
Las horas deberán ir en formato 24 y con los digitos faltantes a 0 HH:mm
Ejemplo: 08:00

## Dias excluidos
conjunto de días en formato de fecha y separados por comas, que no serán fichados
Ejemplo: ["2019-01-01",2019-01-03]

## Días de la semana excluidos
Conjunto de nombre de días en formato español y en minúscula que serán excluidos del fichaje:
Ejemplo: ["lunes","jueves"]

## intervalo de minutos
Número de minutos variable, que modificará aleatoriamente las horas de entrada y salida.
Si no se desea dejar a 0.

## Fines de semana
La aplicación NUNCA imputará en fin de semana.

## Ejemplos de imputaciones
```
"imputaciones": [{
  "tipo": "normal",
  "diaInicio": "2019-07-01",
  "diaFin": "2019-07-09",
  "horaEntrada": "08:00",
  "horaSalida": "15:00",
  "horasEfectivas": "07:00",
  "intervaloVariableMinutos": 0,
  "diasExcluidos": ["2019-07-04"],
  "diasSemanaExcluidos": ["martes"]
}
]
```
Fichará del día 1 de julio hasta el día 9

de 8 a 15 7 horas efectivas

excepto fin de semanas, el día 4 y los días martes

```
{
  "tipo": "diaria",
  "horaEntrada": "08:00",
  "horaSalida": "15:00",
  "horasEfectivas": "07:00",
  "intervaloVariableMinutos": 0,
  "diasExcluidos": ["2019-07-04"],
  "diasSemanaExcluidos": ["martes"]
}
```
Fichará el dia ACTUAL osea hoy,

a menos que sea martes o día 4 de julio

Esto es útil para programar tareas automaticas para el fichaje

```
{
  "tipo": "semanal",
  "horaEntrada": "08:00",
  "horaSalida": "15:00",
  "horasEfectivas": "07:00",
  "intervaloVariableMinutos": 0,
  "diasExcluidos": ["2019-07-04"],
  "diasSemanaExcluidos": ["miercoles","jueves"]
}
```

Fichará el primer día de la semana actual hasta el día actual osea HOY

Menos los dias 4 de julio y los miercoles y jueves.


```
{
  "tipo": "mensual",
  "horaEntrada": "08:00",
  "horaSalida": "15:00",
  "horasEfectivas": "07:00",
  "intervaloVariableMinutos": 0,
}
```

Fichará el primer día del mes actual hasta el día actual osea HOY

menos los findes de semana como en cualquier otro caso.

## Ejemplo complejo
```
{
    "dominio": "empresa.es",
    "usuario": "XX",
    "password": "YY",
    "imputaciones": [
		{
			"tipo": "normal",
            "diaInicio": "2019-07-01",
            "diaFin": "2019-07-05",
            "horaEntrada": "08:00",
            "horaSalida": "15:00",
            "horasEfectivas": "07:00",
            "intervaloVariableMinutos": 0,
            "diasExcluidos": ["2019-07-04"],
			"diasSemanaExcluidos": ["miercoles"]
        },
		{
			"tipo": "semanal",
            "horaEntrada": "08:00",
            "horaSalida": "15:00",
            "horasEfectivas": "07:00",
            "intervaloVariableMinutos": 0,
			"diasSemanaExcluidos": ["martes","jueves"]
        },
		{
			"tipo": "semanal",
            "horaEntrada": "08:00",
            "horaSalida": "18:15",
            "horasEfectivas": "09:00",
            "intervaloVariableMinutos": 5,
			"diasSemanaExcluidos": ["lunes","miercoles","viernes"]
        },
    ]
}
```
Imputará un rango de fechas.

luego semanalmente si se ejecuta un viernes ejecutará toda la semana actual

los lunes miercoles y viernes imputará 7 horas

los martes y jueves imputará 9 horas además cada imputación variará 5 minutos mas o menos en entrada y salida aleatoriamente 

como: (07:55 - 18:10),(08:04 - 18:19) etc...
