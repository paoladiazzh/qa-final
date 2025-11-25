# Unit Testing - Proyecto QA

## Descripción

Proyecto con unit tests completos para las clases `Calculator`, `StringProcessor` y `BankAccount`, siguiendo el patrón AAA (Arrange-Act-Assert) y mejores prácticas de testing

## Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Maven 3.x** - Gestión de dependencias y build
- **JUnit 5.10.0** - Framework de testing
- **JaCoCo 0.8.11** - Análisis de cobertura de código
- **Maven Surefire Plugin 3.1.2** - Ejecución de tests

## Requisitos

- Java 17 o superior
- Maven 3.x

## Ejecutar Tests

### Todos los tests
```bash
mvn test
```

### Tests con reporte de cobertura
```bash
mvn clean test jacoco:report
```

### Tests específicos
```bash
mvn test -Dtest=CalculatorWIPTest
mvn test -Dtest=StringProcessorTest
mvn test -Dtest=BankAccountTest
```

## Reporte de Cobertura

El reporte HTML se genera en: `target/site/jacoco/index.html`

### Cobertura Actual

| Element | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods | Missed | Classes |
|---------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|--------|---------|
| **Total** | 20 of 338 | **94%** | 3 of 56 | **94%** | 7 | 52 | 6 | 86 | 4 | 24 | 1 | 4 |
| org.example | 20 of 338 | **94%** | 3 of 56 | **94%** | 7 | 52 | 6 | 86 | 4 | 24 | 1 | 4 |

Objetivo alcanzado: >80% de cobertura.


## Tests Implementados

### CalculatorWIPTest (22 tests)
Operaciones aritméticas, validación de números pares/impares, valor absoluto, manejo de excepciones.

### StringProcessorTest (35 tests)
Reversión de strings, palíndromos, conteo de vocales, capitalización, validación numérica, manejo de null.

### BankAccountTest (37 tests)
Creación de cuentas, depósitos, retiros, transferencias, estado de cuentas, validación de fondos, excepciones.

**Total: 94 tests**

## Estructura del Proyecto

```
qa-class/
├── pom.xml
├── README.md
├── src/
│   ├── main/java/org/example/
│   │   ├── Calculator.java
│   │   ├── StringProcessor.java
│   │   └── BankAccount.java
│   └── test/java/org/example/wip/
│       ├── CalculatorWIPTest.java
│       ├── StringProcessorTest.java
│       └── BankAccountTest.java
└── target/site/jacoco/index.html
```

## Configuración de Calidad

El build falla automáticamente si la cobertura es menor al 80%.