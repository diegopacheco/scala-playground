### Build
```
sbt compile
```

### Run
```
sbt run
```

### Result

```
success] Total time: 3 s, completed Sep 1, 2024, 2:19:02 PM
sbt:scala-3.5-slick> run
[info] running com.github.diegopacheco.scala35.slick.SlickApplication 
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s2.SUP_ID : Int'
|     2: Path s2.SUP_NAME : String'
|     3: Path s2.STREET : String'
|     4: Path s2.CITY : String'
|     5: Path s2.STATE : String'
|     6: Path s2.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s3.SUP_ID : Int'
|     2: Path s3.SUP_NAME : String'
|     3: Path s3.STREET : String'
|     4: Path s3.CITY : String'
|     5: Path s3.STATE : String'
|     6: Path s3.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|     table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (Int', String', String', String', String', String')
|       1: Path s6.SUP_ID : Int'
|       2: Path s6.SUP_NAME : String'
|       3: Path s6.STREET : String'
|       4: Path s6.CITY : String'
|       5: Path s6.STATE : String'
|       6: Path s6.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: InsertColumn SUP_ID : Int'
|       0: Path s5._1 : Int'
|     2: InsertColumn SUP_NAME : String'
|       0: Path s5._2 : String'
|     3: InsertColumn STREET : String'
|       0: Path s5._3 : String'
|     4: InsertColumn CITY : String'
|       0: Path s5._4 : String'
|     5: InsertColumn STATE : String'
|       0: Path s5._5 : String'
|     6: InsertColumn ZIP : String'
|       0: Path s5._6 : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|   table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (Int', String', String', String', String', String')
|     1: Path s6.SUP_ID : Int'
|     2: Path s6.SUP_NAME : String'
|     3: Path s6.STREET : String'
|     4: Path s6.CITY : String'
|     5: Path s6.STATE : String'
|     6: Path s6.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: BaseResultConverter idx=1, name=SUP_ID : Int'
|   2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|   3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|   4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|   5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|   6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "merge into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: CompiledStatement "merge into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')
|   map: CompiledMapping : (Int', String', String', String', String', String')
|     converter: ProductResultConverter
|       1: BaseResultConverter idx=1, name=SUP_ID : Int'
|       2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|       3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|       4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|       5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|       6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:   33.575782 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:   15.395424 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:   31.769920 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:   43.820907 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:  124.562033 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s2.SUP_ID : Int'
|     2: Path s2.SUP_NAME : String'
|     3: Path s2.STREET : String'
|     4: Path s2.CITY : String'
|     5: Path s2.STATE : String'
|     6: Path s2.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s3.SUP_ID : Int'
|     2: Path s3.SUP_NAME : String'
|     3: Path s3.STREET : String'
|     4: Path s3.CITY : String'
|     5: Path s3.STATE : String'
|     6: Path s3.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|     table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (Int', String', String', String', String', String')
|       1: Path s6.SUP_ID : Int'
|       2: Path s6.SUP_NAME : String'
|       3: Path s6.STREET : String'
|       4: Path s6.CITY : String'
|       5: Path s6.STATE : String'
|       6: Path s6.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: InsertColumn SUP_ID : Int'
|       0: Path s5._1 : Int'
|     2: InsertColumn SUP_NAME : String'
|       0: Path s5._2 : String'
|     3: InsertColumn STREET : String'
|       0: Path s5._3 : String'
|     4: InsertColumn CITY : String'
|       0: Path s5._4 : String'
|     5: InsertColumn STATE : String'
|       0: Path s5._5 : String'
|     6: InsertColumn ZIP : String'
|       0: Path s5._6 : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|   table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (Int', String', String', String', String', String')
|     1: Path s6.SUP_ID : Int'
|     2: Path s6.SUP_NAME : String'
|     3: Path s6.STREET : String'
|     4: Path s6.CITY : String'
|     5: Path s6.STATE : String'
|     6: Path s6.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: BaseResultConverter idx=1, name=SUP_ID : Int'
|   2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|   3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|   4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|   5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|   6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: CompiledStatement "insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')
|   map: CompiledMapping : (Int', String', String', String', String', String')
|     converter: ProductResultConverter
|       1: BaseResultConverter idx=1, name=SUP_ID : Int'
|       2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|       3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|       4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|       5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|       6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    4.357951 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.460731 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:   15.764483 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:   12.392123 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:   32.975288 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s2.SUP_ID : Int'
|     2: Path s2.SUP_NAME : String'
|     3: Path s2.STREET : String'
|     4: Path s2.CITY : String'
|     5: Path s2.STATE : String'
|     6: Path s2.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s3.SUP_ID : Int'
|     2: Path s3.SUP_NAME : String'
|     3: Path s3.STREET : String'
|     4: Path s3.CITY : String'
|     5: Path s3.STATE : String'
|     6: Path s3.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|     table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (Int', String', String', String', String', String')
|       1: Path s6.SUP_ID : Int'
|       2: Path s6.SUP_NAME : String'
|       3: Path s6.STREET : String'
|       4: Path s6.CITY : String'
|       5: Path s6.STATE : String'
|       6: Path s6.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: InsertColumn SUP_ID : Int'
|       0: Path s5._1 : Int'
|     2: InsertColumn SUP_NAME : String'
|       0: Path s5._2 : String'
|     3: InsertColumn STREET : String'
|       0: Path s5._3 : String'
|     4: InsertColumn CITY : String'
|       0: Path s5._4 : String'
|     5: InsertColumn STATE : String'
|       0: Path s5._5 : String'
|     6: InsertColumn ZIP : String'
|       0: Path s5._6 : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|   table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (Int', String', String', String', String', String')
|     1: Path s6.SUP_ID : Int'
|     2: Path s6.SUP_NAME : String'
|     3: Path s6.STREET : String'
|     4: Path s6.CITY : String'
|     5: Path s6.STATE : String'
|     6: Path s6.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: BaseResultConverter idx=1, name=SUP_ID : Int'
|   2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|   3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|   4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|   5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|   6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "merge into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: CompiledStatement "merge into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')
|   map: CompiledMapping : (Int', String', String', String', String', String')
|     converter: ProductResultConverter
|       1: BaseResultConverter idx=1, name=SUP_ID : Int'
|       2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|       3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|       4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|       5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|       6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    4.838905 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.516175 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:   15.208898 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:    9.402278 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:   29.966256 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s2.SUP_ID : Int'
|     2: Path s2.SUP_NAME : String'
|     3: Path s2.STREET : String'
|     4: Path s2.CITY : String'
|     5: Path s2.STATE : String'
|     6: Path s2.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s3.SUP_ID : Int'
|     2: Path s3.SUP_NAME : String'
|     3: Path s3.STREET : String'
|     4: Path s3.CITY : String'
|     5: Path s3.STATE : String'
|     6: Path s3.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|     table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (Int', String', String', String', String', String')
|       1: Path s6.SUP_ID : Int'
|       2: Path s6.SUP_NAME : String'
|       3: Path s6.STREET : String'
|       4: Path s6.CITY : String'
|       5: Path s6.STATE : String'
|       6: Path s6.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: InsertColumn SUP_ID : Int'
|       0: Path s5._1 : Int'
|     2: InsertColumn SUP_NAME : String'
|       0: Path s5._2 : String'
|     3: InsertColumn STREET : String'
|       0: Path s5._3 : String'
|     4: InsertColumn CITY : String'
|       0: Path s5._4 : String'
|     5: InsertColumn STATE : String'
|       0: Path s5._5 : String'
|     6: InsertColumn ZIP : String'
|       0: Path s5._6 : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|   table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (Int', String', String', String', String', String')
|     1: Path s6.SUP_ID : Int'
|     2: Path s6.SUP_NAME : String'
|     3: Path s6.STREET : String'
|     4: Path s6.CITY : String'
|     5: Path s6.STATE : String'
|     6: Path s6.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: BaseResultConverter idx=1, name=SUP_ID : Int'
|   2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|   3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|   4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|   5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|   6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: CompiledStatement "insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')
|   map: CompiledMapping : (Int', String', String', String', String', String')
|     converter: ProductResultConverter
|       1: BaseResultConverter idx=1, name=SUP_ID : Int'
|       2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|       3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|       4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|       5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|       6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    4.802268 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.522432 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:   12.795615 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:    8.741131 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:   26.861446 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s2.SUP_ID : Int'
|     2: Path s2.SUP_NAME : String'
|     3: Path s2.STREET : String'
|     4: Path s2.CITY : String'
|     5: Path s2.STATE : String'
|     6: Path s2.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s3.SUP_ID : Int'
|     2: Path s3.SUP_NAME : String'
|     3: Path s3.STREET : String'
|     4: Path s3.CITY : String'
|     5: Path s3.STATE : String'
|     6: Path s3.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|     table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (Int', String', String', String', String', String')
|       1: Path s6.SUP_ID : Int'
|       2: Path s6.SUP_NAME : String'
|       3: Path s6.STREET : String'
|       4: Path s6.CITY : String'
|       5: Path s6.STATE : String'
|       6: Path s6.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: InsertColumn SUP_ID : Int'
|       0: Path s5._1 : Int'
|     2: InsertColumn SUP_NAME : String'
|       0: Path s5._2 : String'
|     3: InsertColumn STREET : String'
|       0: Path s5._3 : String'
|     4: InsertColumn CITY : String'
|       0: Path s5._4 : String'
|     5: InsertColumn STATE : String'
|       0: Path s5._5 : String'
|     6: InsertColumn ZIP : String'
|       0: Path s5._6 : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|   table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (Int', String', String', String', String', String')
|     1: Path s6.SUP_ID : Int'
|     2: Path s6.SUP_NAME : String'
|     3: Path s6.STREET : String'
|     4: Path s6.CITY : String'
|     5: Path s6.STATE : String'
|     6: Path s6.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: BaseResultConverter idx=1, name=SUP_ID : Int'
|   2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|   3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|   4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|   5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|   6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "merge into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: CompiledStatement "merge into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')
|   map: CompiledMapping : (Int', String', String', String', String', String')
|     converter: ProductResultConverter
|       1: BaseResultConverter idx=1, name=SUP_ID : Int'
|       2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|       3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|       4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|       5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|       6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    3.740864 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.602747 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:    9.270212 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:    6.540420 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:   20.154243 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s2.SUP_ID : Int'
|     2: Path s2.SUP_NAME : String'
|     3: Path s2.STREET : String'
|     4: Path s2.CITY : String'
|     5: Path s2.STATE : String'
|     6: Path s2.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s3.SUP_ID : Int'
|     2: Path s3.SUP_NAME : String'
|     3: Path s3.STREET : String'
|     4: Path s3.CITY : String'
|     5: Path s3.STATE : String'
|     6: Path s3.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|     table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (Int', String', String', String', String', String')
|       1: Path s6.SUP_ID : Int'
|       2: Path s6.SUP_NAME : String'
|       3: Path s6.STREET : String'
|       4: Path s6.CITY : String'
|       5: Path s6.STATE : String'
|       6: Path s6.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: InsertColumn SUP_ID : Int'
|       0: Path s5._1 : Int'
|     2: InsertColumn SUP_NAME : String'
|       0: Path s5._2 : String'
|     3: InsertColumn STREET : String'
|       0: Path s5._3 : String'
|     4: InsertColumn CITY : String'
|       0: Path s5._4 : String'
|     5: InsertColumn STATE : String'
|       0: Path s5._5 : String'
|     6: InsertColumn ZIP : String'
|       0: Path s5._6 : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP] : (Int', String', String', String', String', String')
|   table s6: Table SUPPLIERS : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (Int', String', String', String', String', String')
|     1: Path s6.SUP_ID : Int'
|     2: Path s6.SUP_NAME : String'
|     3: Path s6.STREET : String'
|     4: Path s6.CITY : String'
|     5: Path s6.STATE : String'
|     6: Path s6.ZIP : String'

[sbt-bg-threads-3] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: BaseResultConverter idx=1, name=SUP_ID : Int'
|   2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|   3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|   4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|   5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|   6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s5: CompiledStatement "insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)" : (Int', String', String', String', String', String')
|   map: CompiledMapping : (Int', String', String', String', String', String')
|     converter: ProductResultConverter
|       1: BaseResultConverter idx=1, name=SUP_ID : Int'
|       2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=SUP_NAME : String'
|       3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=STREET : String'
|       4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=CITY : String'
|       5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=STATE : String'
|       6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=ZIP : String'

[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    2.653355 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.337845 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:    8.805746 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:    7.154586 ms
[sbt-bg-threads-3] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:   18.951532 ms
[sbt-bg-threads-3] DEBUG slick.basic.BasicBackend.action - #1: [fused] andThen
      1: schema.create [create table "SUPPLIERS" ("SUP_ID" INTEGER NOT NULL PRIMARY KEY,"SUP_NAME" VARCHAR NOT NULL,"STREET" VARCHAR NOT NULL,"CITY" VARCHAR NOT NULL,"STATE" VARCHAR NOT NULL,"ZIP" VARCHAR NOT NULL); create table "COFFEES" ("COF_NAME" VARCHAR NOT NULL PRIMARY KEY,"SUP_ID" INTEGER NOT NULL,"PRICE" DOUBLE NOT NULL,"SALES" INTEGER NOT NULL,"TOTAL" INTEGER NOT NULL); alter table "COFFEES" add constraint "SUP_FK" foreign key("SUP_ID") references "SUPPLIERS"("SUP_ID") on update NO ACTION on delete NO ACTION]
      2: SingleInsertAction [insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)]
      3: SingleInsertAction [insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)]
      4: SingleInsertAction [insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)]
      5: success ()
[h2mem1-1] DEBUG slick.jdbc.DriverDataSource - Driver org.h2.Driver not already registered; trying to load it
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: create table "SUPPLIERS" ("SUP_ID" INTEGER NOT NULL PRIMARY KEY,"SUP_NAME" VARCHAR NOT NULL,"STREET" VARCHAR NOT NULL,"CITY" VARCHAR NOT NULL,"STATE" VARCHAR NOT NULL,"ZIP" VARCHAR NOT NULL)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep0: create table "SUPPLIERS" ("SUP_ID" INTEGER NOT NULL PRIMARY KEY,"SUP_NAME" VARCHAR NOT NULL,"STREET" VARCHAR NOT NULL,"CITY" VARCHAR NOT NULL,"STATE" VARCHAR NOT NULL,"ZIP" VARCHAR NOT NULL)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep0: create table "SUPPLIERS" ("SUP_ID" INTEGER NOT NULL PRIMARY KEY,"SUP_NAME" VARCHAR NOT NULL,"STREET" VARCHAR NOT NULL,"CITY" VARCHAR NOT NULL,"STATE" VARCHAR NOT NULL,"ZIP" VARCHAR NOT NULL)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 7ms
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: create table "COFFEES" ("COF_NAME" VARCHAR NOT NULL PRIMARY KEY,"SUP_ID" INTEGER NOT NULL,"PRICE" DOUBLE NOT NULL,"SALES" INTEGER NOT NULL,"TOTAL" INTEGER NOT NULL)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep1: create table "COFFEES" ("COF_NAME" VARCHAR NOT NULL PRIMARY KEY,"SUP_ID" INTEGER NOT NULL,"PRICE" DOUBLE NOT NULL,"SALES" INTEGER NOT NULL,"TOTAL" INTEGER NOT NULL)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep1: create table "COFFEES" ("COF_NAME" VARCHAR NOT NULL PRIMARY KEY,"SUP_ID" INTEGER NOT NULL,"PRICE" DOUBLE NOT NULL,"SALES" INTEGER NOT NULL,"TOTAL" INTEGER NOT NULL)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 3ms
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: alter table "COFFEES" add constraint "SUP_FK" foreign key("SUP_ID") references "SUPPLIERS"("SUP_ID") on update NO ACTION on delete NO ACTION
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared statement: prep2: alter table "COFFEES" add constraint "SUP_FK" foreign key("SUP_ID") references "SUPPLIERS"("SUP_ID") on update NO ACTION on delete NO ACTION
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared statement: prep2: alter table "COFFEES" add constraint "SUP_FK" foreign key("SUP_ID") references "SUPPLIERS"("SUP_ID") on update NO ACTION on delete NO ACTION
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared statement took 43ms
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared update: prep3: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?) {1: 101, 2: 'Acme, Inc.', 3: '99 Market Street', 4: 'Groundsville', 5: 'CA', 6: '95199'}
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared update: prep3: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?) {1: 101, 2: 'Acme, Inc.', 3: '99 Market Street', 4: 'Groundsville', 5: 'CA', 6: '95199'}
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - /-----+------------+------------------+--------------+--------+--------\
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | 1   | 2          | 3                | 4            | 5      | 6      |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | Int | String     | String           | String       | String | String |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - |-----+------------+------------------+--------------+--------+--------|
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | 101 | Acme, Inc. | 99 Market Street | Groundsville | CA     | 95199  |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - \-----+------------+------------------+--------------+--------+--------/
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared update took 4ms
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared update: prep4: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?) {1: 49, 2: 'Superior Coffee', 3: '1 Party Place', 4: 'Mendocino', 5: 'CA', 6: '95460'}
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared update: prep4: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?) {1: 49, 2: 'Superior Coffee', 3: '1 Party Place', 4: 'Mendocino', 5: 'CA', 6: '95460'}
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - /-----+-----------------+---------------+-----------+--------+--------\
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | 1   | 2               | 3             | 4         | 5      | 6      |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | Int | String          | String        | String    | String | String |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - |-----+-----------------+---------------+-----------+--------+--------|
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | 49  | Superior Coffee | 1 Party Place | Mendocino | CA     | 95460  |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - \-----+-----------------+---------------+-----------+--------+--------/
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared update took 650µs
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?)
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statement - Executing prepared update: prep5: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?) {1: 150, 2: 'The High Ground', 3: '100 Coffee Lane', 4: 'Meadows', 5: 'CA', 6: '93966'}
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.statementAndParameter - Executing prepared update: prep5: insert into "SUPPLIERS" ("SUP_ID","SUP_NAME","STREET","CITY","STATE","ZIP")  values (?,?,?,?,?,?) {1: 150, 2: 'The High Ground', 3: '100 Coffee Lane', 4: 'Meadows', 5: 'CA', 6: '93966'}
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - /-----+-----------------+-----------------+---------+--------+--------\
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | 1   | 2               | 3               | 4       | 5      | 6      |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | Int | String          | String          | String  | String | String |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - |-----+-----------------+-----------------+---------+--------+--------|
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - | 150 | The High Ground | 100 Coffee Lane | Meadows | CA     | 93966  |
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.parameter - \-----+-----------------+-----------------+---------+--------+--------/
[h2mem1-1] DEBUG slick.jdbc.JdbcBackend.benchmark - Execution of prepared update took 564µs
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table COFFEES
|   columns: ProductNode
|     1: Path s2.COF_NAME : String'
|     2: Path s2.SUP_ID : Int'
|     3: Path s2.PRICE : Double'
|     4: Path s2.SALES : Int'
|     5: Path s2.TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table COFFEES
|   columns: ProductNode
|     1: Path s3.COF_NAME : String'
|     2: Path s3.SUP_ID : Int'
|     3: Path s3.PRICE : Double'
|     4: Path s3.SALES : Int'
|     5: Path s3.TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(String', Int', Double', Int', Int')]
|   from s5: Insert allFields=[COF_NAME, SUP_ID, PRICE, SALES, TOTAL] : (String', Int', Double', Int', Int')
|     table s6: Table COFFEES : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (String', Int', Double', Int', Int')
|       1: Path s6.COF_NAME : String'
|       2: Path s6.SUP_ID : Int'
|       3: Path s6.PRICE : Double'
|       4: Path s6.SALES : Int'
|       5: Path s6.TOTAL : Int'
|   map: ProductNode : (String', Int', Double', Int', Int')
|     1: InsertColumn COF_NAME : String'
|       0: Path s5._1 : String'
|     2: InsertColumn SUP_ID : Int'
|       0: Path s5._2 : Int'
|     3: InsertColumn PRICE : Double'
|       0: Path s5._3 : Double'
|     4: InsertColumn SALES : Int'
|       0: Path s5._4 : Int'
|     5: InsertColumn TOTAL : Int'
|       0: Path s5._5 : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[COF_NAME, SUP_ID, PRICE, SALES, TOTAL] : (String', Int', Double', Int', Int')
|   table s6: Table COFFEES : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (String', Int', Double', Int', Int')
|     1: Path s6.COF_NAME : String'
|     2: Path s6.SUP_ID : Int'
|     3: Path s6.PRICE : Double'
|     4: Path s6.SALES : Int'
|     5: Path s6.TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: SpecializedJdbcResultConverter$$anon$1 idx=1, name=COF_NAME : String'
|   2: BaseResultConverter idx=2, name=SUP_ID : Int'
|   3: BaseResultConverter idx=3, name=PRICE : Double'
|   4: BaseResultConverter idx=4, name=SALES : Int'
|   5: BaseResultConverter idx=5, name=TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "merge into "COFFEES" ("COF_NAME","SUP_ID","PRICE","SALES","TOTAL")  values (?,?,?,?,?)" : (String', Int', Double', Int', Int')

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(String', Int', Double', Int', Int')]
|   from s5: CompiledStatement "merge into "COFFEES" ("COF_NAME","SUP_ID","PRICE","SALES","TOTAL")  values (?,?,?,?,?)" : (String', Int', Double', Int', Int')
|   map: CompiledMapping : (String', Int', Double', Int', Int')
|     converter: ProductResultConverter
|       1: SpecializedJdbcResultConverter$$anon$1 idx=1, name=COF_NAME : String'
|       2: BaseResultConverter idx=2, name=SUP_ID : Int'
|       3: BaseResultConverter idx=3, name=PRICE : Double'
|       4: BaseResultConverter idx=4, name=SALES : Int'
|       5: BaseResultConverter idx=5, name=TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    2.641039 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.501823 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:    7.821666 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:    6.117615 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:   17.082143 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table COFFEES
|   columns: ProductNode
|     1: Path s2.COF_NAME : String'
|     2: Path s2.SUP_ID : Int'
|     3: Path s2.PRICE : Double'
|     4: Path s2.SALES : Int'
|     5: Path s2.TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table COFFEES
|   columns: ProductNode
|     1: Path s3.COF_NAME : String'
|     2: Path s3.SUP_ID : Int'
|     3: Path s3.PRICE : Double'
|     4: Path s3.SALES : Int'
|     5: Path s3.TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase insertCompiler:
| ResultSetMapping : Vector[(String', Int', Double', Int', Int')]
|   from s5: Insert allFields=[COF_NAME, SUP_ID, PRICE, SALES, TOTAL] : (String', Int', Double', Int', Int')
|     table s6: Table COFFEES : Vector[@t4<UnassignedType>]
|     linear: ProductNode : (String', Int', Double', Int', Int')
|       1: Path s6.COF_NAME : String'
|       2: Path s6.SUP_ID : Int'
|       3: Path s6.PRICE : Double'
|       4: Path s6.SALES : Int'
|       5: Path s6.TOTAL : Int'
|   map: ProductNode : (String', Int', Double', Int', Int')
|     1: InsertColumn COF_NAME : String'
|       0: Path s5._1 : String'
|     2: InsertColumn SUP_ID : Int'
|       0: Path s5._2 : Int'
|     3: InsertColumn PRICE : Double'
|       0: Path s5._3 : Double'
|     4: InsertColumn SALES : Int'
|       0: Path s5._4 : Int'
|     5: InsertColumn TOTAL : Int'
|       0: Path s5._5 : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Insert allFields=[COF_NAME, SUP_ID, PRICE, SALES, TOTAL] : (String', Int', Double', Int', Int')
|   table s6: Table COFFEES : Vector[@t4<UnassignedType>]
|   linear: ProductNode : (String', Int', Double', Int', Int')
|     1: Path s6.COF_NAME : String'
|     2: Path s6.SUP_ID : Int'
|     3: Path s6.PRICE : Double'
|     4: Path s6.SALES : Int'
|     5: Path s6.TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: SpecializedJdbcResultConverter$$anon$1 idx=1, name=COF_NAME : String'
|   2: BaseResultConverter idx=2, name=SUP_ID : Int'
|   3: BaseResultConverter idx=3, name=PRICE : Double'
|   4: BaseResultConverter idx=4, name=SALES : Int'
|   5: BaseResultConverter idx=5, name=TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "insert into "COFFEES" ("COF_NAME","SUP_ID","PRICE","SALES","TOTAL")  values (?,?,?,?,?)" : (String', Int', Double', Int', Int')

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(String', Int', Double', Int', Int')]
|   from s5: CompiledStatement "insert into "COFFEES" ("COF_NAME","SUP_ID","PRICE","SALES","TOTAL")  values (?,?,?,?,?)" : (String', Int', Double', Int', Int')
|   map: CompiledMapping : (String', Int', Double', Int', Int')
|     converter: ProductResultConverter
|       1: SpecializedJdbcResultConverter$$anon$1 idx=1, name=COF_NAME : String'
|       2: BaseResultConverter idx=2, name=SUP_ID : Int'
|       3: BaseResultConverter idx=3, name=PRICE : Double'
|       4: BaseResultConverter idx=4, name=SALES : Int'
|       5: BaseResultConverter idx=5, name=TOTAL : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    3.949074 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.361533 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -            insertCompiler:    7.084527 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:    5.344586 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:   16.739720 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - Source:
| TableExpansion
|   table s2: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s2.SUP_ID : Int'
|     2: Path s2.SUP_NAME : String'
|     3: Path s2.STREET : String'
|     4: Path s2.CITY : String'
|     5: Path s2.STATE : String'
|     6: Path s2.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.compiler.AssignUniqueSymbols - Detected features: UsedFeatures(false,false,false,false)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase assignUniqueSymbols:
| TableExpansion
|   table s3: Table SUPPLIERS
|   columns: ProductNode
|     1: Path s3.SUP_ID : Int'
|     2: Path s3.SUP_NAME : String'
|     3: Path s3.STREET : String'
|     4: Path s3.CITY : String'
|     5: Path s3.STATE : String'
|     6: Path s3.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase unrollTailBinds: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase inferTypes: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.ExpandTables - Found Selects for NominalTypes: @(slick.jdbc.H2Profile$._.SUPPLIERS)
[scala-execution-context-global-432] DEBUG slick.compiler.ExpandTables - With correct table types:
| Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]

[scala-execution-context-global-432] DEBUG slick.compiler.ExpandTables - Table expansions: @t4 -> (s3,ProductNode)
[scala-execution-context-global-432] DEBUG slick.compiler.ExpandTables - Expanding tables in result type
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase expandTables:
| Bind : Vector[t6<(Int', String', String', String', String', String')>]
|   from s5: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|   select: Pure t6 : Vector[t6<(Int', String', String', String', String', String')>]
|     value: ProductNode : (Int', String', String', String', String', String')
|       1: Path s5.SUP_ID : Int'
|       2: Path s5.SUP_NAME : String'
|       3: Path s5.STREET : String'
|       4: Path s5.CITY : String'
|       5: Path s5.STATE : String'
|       6: Path s5.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase forceOuterBinds:
| Bind : Vector[t6<(Int', String', String', String', String', String')>]
|   from s5: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|   select: Pure t6 : Vector[t6<(Int', String', String', String', String', String')>]
|     value: ProductNode : (Int', String', String', String', String', String')
|       1: Path s5.SUP_ID : Int'
|       2: Path s5.SUP_NAME : String'
|       3: Path s5.STREET : String'
|       4: Path s5.CITY : String'
|       5: Path s5.STATE : String'
|       6: Path s5.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase removeMappedTypes: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase expandSums: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase emulateOuterJoins: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase expandRecords:
| Bind : Vector[t6<(Int', String', String', String', String', String')>]
|   from s5: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|   select: Pure t6 : Vector[t6<(Int', String', String', String', String', String')>]
|     value: ProductNode : (Int', String', String', String', String', String')
|       1: Path s5.SUP_ID : Int'
|       2: Path s5.SUP_NAME : String'
|       3: Path s5.STREET : String'
|       4: Path s5.CITY : String'
|       5: Path s5.STATE : String'
|       6: Path s5.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening projection t6
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Analyzing s5.SUP_ID with symbols 
| Path s5.SUP_ID : Int'

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Translated s5.SUP_ID to:
| Path s5.SUP_ID

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Analyzing s5.SUP_NAME with symbols 
| Path s5.SUP_NAME : String'

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Translated s5.SUP_NAME to:
| Path s5.SUP_NAME

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Analyzing s5.STREET with symbols 
| Path s5.STREET : String'

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Translated s5.STREET to:
| Path s5.STREET

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Analyzing s5.CITY with symbols 
| Path s5.CITY : String'

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Translated s5.CITY to:
| Path s5.CITY

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Analyzing s5.STATE with symbols 
| Path s5.STATE : String'

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Translated s5.STATE to:
| Path s5.STATE

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Analyzing s5.ZIP with symbols 
| Path s5.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Translated s5.ZIP to:
| Path s5.ZIP

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening node at Path 
| ProductNode
|   1: Path s5.SUP_ID
|   2: Path s5.SUP_NAME
|   3: Path s5.STREET
|   4: Path s5.CITY
|   5: Path s5.STATE
|   6: Path s5.ZIP

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening node at Path _1
| Path s5.SUP_ID

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Adding definition: s7 -> Path s5.SUP_ID
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening node at Path _2
| Path s5.SUP_NAME

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Adding definition: s8 -> Path s5.SUP_NAME
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening node at Path _3
| Path s5.STREET

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Adding definition: s9 -> Path s5.STREET
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening node at Path _4
| Path s5.CITY

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Adding definition: s10 -> Path s5.CITY
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening node at Path _5
| Path s5.STATE

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Adding definition: s11 -> Path s5.STATE
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattening node at Path _6
| Path s5.ZIP

[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Adding definition: s12 -> Path s5.ZIP
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Adding translation for t6: (HashMap(List(_5) -> s11, List(_2) -> s8, List(_6) -> s12, List(_4) -> s10, List(_1) -> s7, List(_3) -> s9), UnassignedType)
[scala-execution-context-global-432] DEBUG slick.compiler.FlattenProjections - Flattened projection to
| Pure t6
|   value: StructNode
|     s7: Path s5.SUP_ID
|     s8: Path s5.SUP_NAME
|     s9: Path s5.STREET
|     s10: Path s5.CITY
|     s11: Path s5.STATE
|     s12: Path s5.ZIP

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase flattenProjections:
| Bind : Vector[t6<{s7: Int', s8: String', s9: String', s10: String', s11: String', s12: String'}>]
|   from s5: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|   select: Pure t6 : Vector[t6<{s7: Int', s8: String', s9: String', s10: String', s11: String', s12: String'}>]
|     value: StructNode : {s7: Int', s8: String', s9: String', s10: String', s11: String', s12: String'}
|       s7: Path s5.SUP_ID : Int'
|       s8: Path s5.SUP_NAME : String'
|       s9: Path s5.STREET : String'
|       s10: Path s5.CITY : String'
|       s11: Path s5.STATE : String'
|       s12: Path s5.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase rewriteJoins: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase verifySymbols: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase relabelUnions: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase createAggregates: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase resolveZipJoins: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.PruneProjections - Unreferenced: t6; Field refs: (@t4,STREET), (@t4,SUP_ID), (@t4,SUP_NAME), (@t4,ZIP), (@t4,CITY), (@t4,STATE)
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase pruneProjections: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.RewriteDistinct - No DISTINCT used as determined by assignUniqueSymbols - skipping phase
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase rewriteDistinct: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.CreateResultSetMapping - Creating mapping from t6<(Int', String', String', String', String', String')>
[scala-execution-context-global-432] DEBUG slick.compiler.CreateResultSetMapping - Creating mapping from Int'
[scala-execution-context-global-432] DEBUG slick.compiler.CreateResultSetMapping - Creating mapping from String'
[scala-execution-context-global-432] DEBUG slick.compiler.CreateResultSetMapping - Creating mapping from String'
[scala-execution-context-global-432] DEBUG slick.compiler.CreateResultSetMapping - Creating mapping from String'
[scala-execution-context-global-432] DEBUG slick.compiler.CreateResultSetMapping - Creating mapping from String'
[scala-execution-context-global-432] DEBUG slick.compiler.CreateResultSetMapping - Creating mapping from String'
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase createResultSetMapping:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s13: Bind : Vector[t6<{s7: Int', s8: String', s9: String', s10: String', s11: String', s12: String'}>]
|     from s5: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|     select: Pure t6 : Vector[t6<{s7: Int', s8: String', s9: String', s10: String', s11: String', s12: String'}>]
|       value: StructNode : {s7: Int', s8: String', s9: String', s10: String', s11: String', s12: String'}
|         s7: Path s5.SUP_ID : Int'
|         s8: Path s5.SUP_NAME : String'
|         s9: Path s5.STREET : String'
|         s10: Path s5.CITY : String'
|         s11: Path s5.STATE : String'
|         s12: Path s5.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: Path s13.s7 : Int'
|     2: Path s13.s8 : String'
|     3: Path s13.s9 : String'
|     4: Path s13.s10 : String'
|     5: Path s13.s11 : String'
|     6: Path s13.s12 : String'

[scala-execution-context-global-432] DEBUG slick.compiler.HoistClientOps - Hoisting operations from defs: 
[scala-execution-context-global-432] DEBUG slick.compiler.HoistClientOps - New defs: HashMap(0 -> (Path s5.SUP_ID,s14), 5 -> (Path s5.ZIP,s15), 1 -> (Path s5.SUP_NAME,s16), 2 -> (Path s5.STREET,s17), 3 -> (Path s5.CITY,s18), 4 -> (Path s5.STATE,s19))
[scala-execution-context-global-432] DEBUG slick.compiler.HoistClientOps - New ResultSetMapping:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s13: Bind : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|     from s5: ...
|     select: Pure t20 : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|       value: StructNode : {s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}
|         s18: Path s5.CITY : String'
|         s15: Path s5.ZIP : String'
|         s17: Path s5.STREET : String'
|         s16: Path s5.SUP_NAME : String'
|         s14: Path s5.SUP_ID : Int'
|         s19: Path s5.STATE : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: Path s13.s14 : Int'
|     2: Path s13.s16 : String'
|     3: Path s13.s17 : String'
|     4: Path s13.s18 : String'
|     5: Path s13.s19 : String'
|     6: Path s13.s15 : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase hoistClientOps:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s13: Bind : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|     from s5: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|     select: Pure t20 : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|       value: StructNode : {s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}
|         s18: Path s5.CITY : String'
|         s15: Path s5.ZIP : String'
|         s17: Path s5.STREET : String'
|         s16: Path s5.SUP_NAME : String'
|         s14: Path s5.SUP_ID : Int'
|         s19: Path s5.STATE : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: Path s13.s14 : Int'
|     2: Path s13.s16 : String'
|     3: Path s13.s17 : String'
|     4: Path s13.s18 : String'
|     5: Path s13.s19 : String'
|     6: Path s13.s15 : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase reorderOperations: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Table fields: HashMap(@t4 -> Vector(CITY, ZIP, STREET, SUP_NAME, SUP_ID, STATE))
[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Creating source from TableNode:
| Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]

[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Mappings are: ConstArray(((@t4,CITY),List(CITY)), ((@t4,ZIP),List(ZIP)), ((@t4,STREET),List(STREET)), ((@t4,SUP_NAME),List(SUP_NAME)), ((@t4,SUP_ID),List(SUP_ID)), ((@t4,STATE),List(STATE)))
[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Building new Comprehension from:
| Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]

[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Built new Comprehension:
| Comprehension s21, None, None, ConstArray(), None, None, None, None, false : Vector[t22<{s23: String', s24: String', s25: String', s26: String', s27: Int', s28: String'}>]
|   from s21: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|   select: Pure t22 : Vector[t22<{s23: String', s24: String', s25: String', s26: String', s27: Int', s28: String'}>]
|     value: StructNode : {s23: String', s24: String', s25: String', s26: String', s27: Int', s28: String'}
|       s23: Path s21.CITY : String'
|       s24: Path s21.ZIP : String'
|       s25: Path s21.STREET : String'
|       s26: Path s21.SUP_NAME : String'
|       s27: Path s21.SUP_ID : Int'
|       s28: Path s21.STATE : String'

[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Replacements are: HashMap((@t4,STREET) -> s25, (@t4,SUP_ID) -> s27, (@t4,SUP_NAME) -> s26, (@t4,ZIP) -> s24, (@t4,CITY) -> s23, (@t4,STATE) -> s28)
[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Merging Bind into Comprehension as 'select':
| Bind : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|   from s5: ...
|   select: Pure t20 : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|     value: StructNode : {s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}
|       s18: Path s5.CITY : String'
|       s15: Path s5.ZIP : String'
|       s17: Path s5.STREET : String'
|       s16: Path s5.SUP_NAME : String'
|       s14: Path s5.SUP_ID : Int'
|       s19: Path s5.STATE : String'

[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Merged Bind into Comprehension as 'select':
| Comprehension s21, None, None, ConstArray(), None, None, None, None, false : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|   from s21: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|   select: Pure t20 : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|     value: StructNode : {s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}
|       s18: Path s21.CITY : String'
|       s15: Path s21.ZIP : String'
|       s17: Path s21.STREET : String'
|       s16: Path s21.SUP_NAME : String'
|       s14: Path s21.SUP_ID : Int'
|       s19: Path s21.STATE : String'

[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Replacements are: HashMap((t20,s18) -> s18, (t20,s17) -> s17, (t20,s15) -> s15, (t20,s16) -> s16, (t20,s19) -> s19, (t20,s14) -> s14)
[scala-execution-context-global-432] DEBUG slick.compiler.MergeToComprehensions - Mappings are: ConstArray(((t20,s18),List(s18)), ((t20,s17),List(s17)), ((t20,s15),List(s15)), ((t20,s16),List(s16)), ((t20,s19),List(s19)), ((t20,s14),List(s14)))
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase mergeToComprehensions:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s13: Comprehension s21, None, None, ConstArray(), None, None, None, None, false : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|     from s21: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|     select: Pure t20 : Vector[t20<{s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}>]
|       value: StructNode : {s18: String', s15: String', s17: String', s16: String', s14: Int', s19: String'}
|         s18: Path s21.CITY : String'
|         s15: Path s21.ZIP : String'
|         s17: Path s21.STREET : String'
|         s16: Path s21.SUP_NAME : String'
|         s14: Path s21.SUP_ID : Int'
|         s19: Path s21.STATE : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: Path s13.s14 : Int'
|     2: Path s13.s16 : String'
|     3: Path s13.s17 : String'
|     4: Path s13.s18 : String'
|     5: Path s13.s19 : String'
|     6: Path s13.s15 : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase optimizeScalar: (no change)
[scala-execution-context-global-432] DEBUG slick.compiler.RemoveFieldNames - Required symbols: s18 -> 3, s15 -> 5, s17 -> 2, s16 -> 1, s14 -> 0, s19 -> 4
[scala-execution-context-global-432] DEBUG slick.compiler.RemoveFieldNames - Transformed RSM: 
| ResultSetMapping
|   from s13: Comprehension s21, None, None, ConstArray(), None, None, None, None, false : Vector[t20<(Int', String', String', String', String', String')>]
|     from s21: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|     select: Pure t20 : Vector[t20<(Int', String', String', String', String', String')>]
|       value: ProductNode : (Int', String', String', String', String', String')
|         1: Path s21.SUP_ID : Int'
|         2: Path s21.SUP_NAME : String'
|         3: Path s21.STREET : String'
|         4: Path s21.CITY : String'
|         5: Path s21.STATE : String'
|         6: Path s21.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: Path s13.s14 : Int'
|     2: Path s13.s16 : String'
|     3: Path s13.s17 : String'
|     4: Path s13.s18 : String'
|     5: Path s13.s19 : String'
|     6: Path s13.s15 : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase removeFieldNames:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s13: Comprehension s21, None, None, ConstArray(), None, None, None, None, false : Vector[t20<(Int', String', String', String', String', String')>]
|     from s21: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|     select: Pure t20 : Vector[t20<(Int', String', String', String', String', String')>]
|       value: ProductNode : (Int', String', String', String', String', String')
|         1: Path s21.SUP_ID : Int'
|         2: Path s21.SUP_NAME : String'
|         3: Path s21.STREET : String'
|         4: Path s21.CITY : String'
|         5: Path s21.STATE : String'
|         6: Path s21.ZIP : String'
|   map: ProductNode : (Int', String', String', String', String', String')
|     1: Path s13._1 : Int'
|     2: Path s13._2 : String'
|     3: Path s13._3 : String'
|     4: Path s13._4 : String'
|     5: Path s13._5 : String'
|     6: Path s13._6 : String'

[scala-execution-context-global-432] DEBUG slick.compiler.CodeGen - Compiling server-side and mapping with server-side:
| Comprehension s21, None, None, ConstArray(), None, None, None, None, false : Vector[t20<(Int', String', String', String', String', String')>]
|   from s21: Table SUPPLIERS : Vector[@t4<{STREET: String', CITY: String', SUP_ID: Int', STATE: String', ZIP: String', SUP_NAME: String'}>]
|   select: Pure t20 : Vector[t20<(Int', String', String', String', String', String')>]
|     value: ProductNode : (Int', String', String', String', String', String')
|       1: Path s21.SUP_ID : Int'
|       2: Path s21.SUP_NAME : String'
|       3: Path s21.STREET : String'
|       4: Path s21.CITY : String'
|       5: Path s21.STATE : String'
|       6: Path s21.ZIP : String'

[scala-execution-context-global-432] DEBUG slick.relational.ResultConverterCompiler - Compiled ResultConverter
| ProductResultConverter
|   1: BaseResultConverter idx=1, name=<computed> : Int'
|   2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=<computed> : String'
|   3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=<computed> : String'
|   4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=<computed> : String'
|   5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=<computed> : String'
|   6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=<computed> : String'

[scala-execution-context-global-432] DEBUG slick.compiler.CodeGen - Compiled server-side to:
| CompiledStatement "select "SUP_ID", "SUP_NAME", "STREET", "CITY", "STATE", "ZIP" from "SUPPLIERS"" : Vector[t20<(Int', String', String', String', String', String')>]

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompiler - After phase codeGen:
| ResultSetMapping : Vector[(Int', String', String', String', String', String')]
|   from s13: CompiledStatement "select "SUP_ID", "SUP_NAME", "STREET", "CITY", "STATE", "ZIP" from "SUPPLIERS"" : Vector[t20<(Int', String', String', String', String', String')>]
|   map: CompiledMapping : (Int', String', String', String', String', String')
|     converter: ProductResultConverter
|       1: BaseResultConverter idx=1, name=<computed> : Int'
|       2: SpecializedJdbcResultConverter$$anon$1 idx=2, name=<computed> : String'
|       3: SpecializedJdbcResultConverter$$anon$1 idx=3, name=<computed> : String'
|       4: SpecializedJdbcResultConverter$$anon$1 idx=4, name=<computed> : String'
|       5: SpecializedJdbcResultConverter$$anon$1 idx=5, name=<computed> : String'
|       6: SpecializedJdbcResultConverter$$anon$1 idx=6, name=<computed> : String'

[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark - ------------------- Phase: Time ---------
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -       assignUniqueSymbols:    1.860918 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -           unrollTailBinds:    3.626276 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                inferTypes:    0.337413 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -              expandTables:   33.976751 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -           forceOuterBinds:    7.291694 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -         removeMappedTypes:    1.725243 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                expandSums:    1.432918 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -         emulateOuterJoins:    2.363110 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -             expandRecords:    5.188597 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -        flattenProjections:   30.901245 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -              rewriteJoins:    1.737129 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -             verifySymbols:    5.626772 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -             relabelUnions:    4.246970 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -          createAggregates:    1.629096 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -           resolveZipJoins:    2.056486 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -          pruneProjections:    8.616519 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -           rewriteDistinct:    1.799178 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -    createResultSetMapping:   12.288736 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -            hoistClientOps:   40.204908 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -         reorderOperations:    6.520382 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -     mergeToComprehensions:   61.632506 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -            optimizeScalar:    3.440745 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -          removeFieldNames:   24.514489 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                   codeGen:   49.672069 ms
[scala-execution-context-global-432] DEBUG slick.compiler.QueryCompilerBenchmark -                     TOTAL:  312.690150 ms
```

