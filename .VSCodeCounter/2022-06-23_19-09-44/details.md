# Details

Date : 2022-06-23 19:09:44

Directory \\\\wsl$\\Ubuntu-22.04\\home\\kitagrace\\VJVM\\VJVM-public\\src

Total : 76 files,  3765 codes, 315 comments, 827 blanks, all 4907 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/main/java/vjvm/classfiledefs/AttributeTags.java](/src/main/java/vjvm/classfiledefs/AttributeTags.java) | Java | 31 | 3 | 2 | 36 |
| [src/main/java/vjvm/classfiledefs/ClassAccessFlags.java](/src/main/java/vjvm/classfiledefs/ClassAccessFlags.java) | Java | 12 | 3 | 2 | 17 |
| [src/main/java/vjvm/classfiledefs/ConstantTags.java](/src/main/java/vjvm/classfiledefs/ConstantTags.java) | Java | 20 | 3 | 2 | 25 |
| [src/main/java/vjvm/classfiledefs/Descriptors.java](/src/main/java/vjvm/classfiledefs/Descriptors.java) | Java | 50 | 6 | 7 | 63 |
| [src/main/java/vjvm/classfiledefs/FieldAccessFlags.java](/src/main/java/vjvm/classfiledefs/FieldAccessFlags.java) | Java | 12 | 3 | 2 | 17 |
| [src/main/java/vjvm/classfiledefs/MethodAccessFlags.java](/src/main/java/vjvm/classfiledefs/MethodAccessFlags.java) | Java | 15 | 3 | 2 | 20 |
| [src/main/java/vjvm/classfiledefs/MethodDescriptors.java](/src/main/java/vjvm/classfiledefs/MethodDescriptors.java) | Java | 41 | 5 | 6 | 52 |
| [src/main/java/vjvm/classfiledefs/Opcodes.java](/src/main/java/vjvm/classfiledefs/Opcodes.java) | Java | 208 | 14 | 12 | 234 |
| [src/main/java/vjvm/classloader/JClassLoader.java](/src/main/java/vjvm/classloader/JClassLoader.java) | Java | 57 | 10 | 7 | 74 |
| [src/main/java/vjvm/classloader/searchpath/ClassSearchPath.java](/src/main/java/vjvm/classloader/searchpath/ClassSearchPath.java) | Java | 51 | 19 | 11 | 81 |
| [src/main/java/vjvm/classloader/searchpath/ModuleSearchPath.java](/src/main/java/vjvm/classloader/searchpath/ModuleSearchPath.java) | Java | 46 | 0 | 10 | 56 |
| [src/main/java/vjvm/interpreter/Breakpoint.java](/src/main/java/vjvm/interpreter/Breakpoint.java) | Java | 37 | 0 | 9 | 46 |
| [src/main/java/vjvm/interpreter/JInterpreter.java](/src/main/java/vjvm/interpreter/JInterpreter.java) | Java | 134 | 12 | 25 | 171 |
| [src/main/java/vjvm/interpreter/JMonitor.java](/src/main/java/vjvm/interpreter/JMonitor.java) | Java | 207 | 6 | 41 | 254 |
| [src/main/java/vjvm/interpreter/instruction/Decoder.java](/src/main/java/vjvm/interpreter/instruction/Decoder.java) | Java | 105 | 2 | 9 | 116 |
| [src/main/java/vjvm/interpreter/instruction/Instruction.java](/src/main/java/vjvm/interpreter/instruction/Instruction.java) | Java | 7 | 0 | 4 | 11 |
| [src/main/java/vjvm/interpreter/instruction/comparisons/IFCOND.java](/src/main/java/vjvm/interpreter/instruction/comparisons/IFCOND.java) | Java | 92 | 4 | 16 | 112 |
| [src/main/java/vjvm/interpreter/instruction/comparisons/IF_XCMPCOND.java](/src/main/java/vjvm/interpreter/instruction/comparisons/IF_XCMPCOND.java) | Java | 94 | 0 | 15 | 109 |
| [src/main/java/vjvm/interpreter/instruction/comparisons/LCMP.java](/src/main/java/vjvm/interpreter/instruction/comparisons/LCMP.java) | Java | 33 | 0 | 7 | 40 |
| [src/main/java/vjvm/interpreter/instruction/comparisons/XCMPCOND.java](/src/main/java/vjvm/interpreter/instruction/comparisons/XCMPCOND.java) | Java | 49 | 0 | 10 | 59 |
| [src/main/java/vjvm/interpreter/instruction/constants/LDCX.java](/src/main/java/vjvm/interpreter/instruction/constants/LDCX.java) | Java | 66 | 3 | 11 | 80 |
| [src/main/java/vjvm/interpreter/instruction/constants/NOP.java](/src/main/java/vjvm/interpreter/instruction/constants/NOP.java) | Java | 18 | 0 | 4 | 22 |
| [src/main/java/vjvm/interpreter/instruction/constants/XCONST_Y.java](/src/main/java/vjvm/interpreter/instruction/constants/XCONST_Y.java) | Java | 68 | 4 | 20 | 92 |
| [src/main/java/vjvm/interpreter/instruction/constants/XPUSH.java](/src/main/java/vjvm/interpreter/instruction/constants/XPUSH.java) | Java | 29 | 3 | 7 | 39 |
| [src/main/java/vjvm/interpreter/instruction/control/GOTO.java](/src/main/java/vjvm/interpreter/instruction/control/GOTO.java) | Java | 27 | 0 | 6 | 33 |
| [src/main/java/vjvm/interpreter/instruction/control/XRETURN.java](/src/main/java/vjvm/interpreter/instruction/control/XRETURN.java) | Java | 46 | 0 | 12 | 58 |
| [src/main/java/vjvm/interpreter/instruction/conversions/X2Y.java](/src/main/java/vjvm/interpreter/instruction/conversions/X2Y.java) | Java | 75 | 0 | 22 | 97 |
| [src/main/java/vjvm/interpreter/instruction/loads/XLOAD.java](/src/main/java/vjvm/interpreter/instruction/loads/XLOAD.java) | Java | 86 | 3 | 26 | 115 |
| [src/main/java/vjvm/interpreter/instruction/math/IINC.java](/src/main/java/vjvm/interpreter/instruction/math/IINC.java) | Java | 15 | 0 | 6 | 21 |
| [src/main/java/vjvm/interpreter/instruction/math/LIOPR.java](/src/main/java/vjvm/interpreter/instruction/math/LIOPR.java) | Java | 15 | 0 | 5 | 20 |
| [src/main/java/vjvm/interpreter/instruction/math/XNEG.java](/src/main/java/vjvm/interpreter/instruction/math/XNEG.java) | Java | 15 | 0 | 5 | 20 |
| [src/main/java/vjvm/interpreter/instruction/math/XOPR.java](/src/main/java/vjvm/interpreter/instruction/math/XOPR.java) | Java | 15 | 0 | 5 | 20 |
| [src/main/java/vjvm/interpreter/instruction/references/INVOKESTATIC.java](/src/main/java/vjvm/interpreter/instruction/references/INVOKESTATIC.java) | Java | 43 | 9 | 13 | 65 |
| [src/main/java/vjvm/interpreter/instruction/reserved/BREAKPOINT.java](/src/main/java/vjvm/interpreter/instruction/reserved/BREAKPOINT.java) | Java | 20 | 0 | 5 | 25 |
| [src/main/java/vjvm/interpreter/instruction/stack/DUPX.java](/src/main/java/vjvm/interpreter/instruction/stack/DUPX.java) | Java | 39 | 0 | 10 | 49 |
| [src/main/java/vjvm/interpreter/instruction/stack/DUPX_XY.java](/src/main/java/vjvm/interpreter/instruction/stack/DUPX_XY.java) | Java | 39 | 0 | 9 | 48 |
| [src/main/java/vjvm/interpreter/instruction/stack/POPX.java](/src/main/java/vjvm/interpreter/instruction/stack/POPX.java) | Java | 32 | 3 | 8 | 43 |
| [src/main/java/vjvm/interpreter/instruction/stack/SWAP.java](/src/main/java/vjvm/interpreter/instruction/stack/SWAP.java) | Java | 29 | 0 | 5 | 34 |
| [src/main/java/vjvm/interpreter/instruction/stores/XSTORE.java](/src/main/java/vjvm/interpreter/instruction/stores/XSTORE.java) | Java | 87 | 5 | 27 | 119 |
| [src/main/java/vjvm/runtime/JClass.java](/src/main/java/vjvm/runtime/JClass.java) | Java | 107 | 7 | 27 | 141 |
| [src/main/java/vjvm/runtime/JFrame.java](/src/main/java/vjvm/runtime/JFrame.java) | Java | 30 | 0 | 6 | 36 |
| [src/main/java/vjvm/runtime/JThread.java](/src/main/java/vjvm/runtime/JThread.java) | Java | 32 | 2 | 13 | 47 |
| [src/main/java/vjvm/runtime/OperandStack.java](/src/main/java/vjvm/runtime/OperandStack.java) | Java | 128 | 41 | 24 | 193 |
| [src/main/java/vjvm/runtime/ProgramCounter.java](/src/main/java/vjvm/runtime/ProgramCounter.java) | Java | 29 | 0 | 11 | 40 |
| [src/main/java/vjvm/runtime/Slots.java](/src/main/java/vjvm/runtime/Slots.java) | Java | 230 | 57 | 28 | 315 |
| [src/main/java/vjvm/runtime/classdata/ConstantPool.java](/src/main/java/vjvm/runtime/classdata/ConstantPool.java) | Java | 34 | 13 | 8 | 55 |
| [src/main/java/vjvm/runtime/classdata/FieldInfo.java](/src/main/java/vjvm/runtime/classdata/FieldInfo.java) | Java | 71 | 11 | 21 | 103 |
| [src/main/java/vjvm/runtime/classdata/Interface.java](/src/main/java/vjvm/runtime/classdata/Interface.java) | Java | 20 | 13 | 7 | 40 |
| [src/main/java/vjvm/runtime/classdata/MethodInfo.java](/src/main/java/vjvm/runtime/classdata/MethodInfo.java) | Java | 89 | 11 | 29 | 129 |
| [src/main/java/vjvm/runtime/classdata/attribute/Attribute.java](/src/main/java/vjvm/runtime/classdata/attribute/Attribute.java) | Java | 29 | 11 | 8 | 48 |
| [src/main/java/vjvm/runtime/classdata/attribute/Code.java](/src/main/java/vjvm/runtime/classdata/attribute/Code.java) | Java | 27 | 2 | 8 | 37 |
| [src/main/java/vjvm/runtime/classdata/attribute/UnknownAttribute.java](/src/main/java/vjvm/runtime/classdata/attribute/UnknownAttribute.java) | Java | 13 | 0 | 5 | 18 |
| [src/main/java/vjvm/runtime/classdata/constant/ClassConstant.java](/src/main/java/vjvm/runtime/classdata/constant/ClassConstant.java) | Java | 37 | 4 | 8 | 49 |
| [src/main/java/vjvm/runtime/classdata/constant/Constant.java](/src/main/java/vjvm/runtime/classdata/constant/Constant.java) | Java | 74 | 7 | 6 | 87 |
| [src/main/java/vjvm/runtime/classdata/constant/DoubleConstant.java](/src/main/java/vjvm/runtime/classdata/constant/DoubleConstant.java) | Java | 16 | 0 | 6 | 22 |
| [src/main/java/vjvm/runtime/classdata/constant/FieldrefConstant.java](/src/main/java/vjvm/runtime/classdata/constant/FieldrefConstant.java) | Java | 28 | 0 | 7 | 35 |
| [src/main/java/vjvm/runtime/classdata/constant/FloatConstant.java](/src/main/java/vjvm/runtime/classdata/constant/FloatConstant.java) | Java | 16 | 0 | 6 | 22 |
| [src/main/java/vjvm/runtime/classdata/constant/IntegerConstant.java](/src/main/java/vjvm/runtime/classdata/constant/IntegerConstant.java) | Java | 16 | 0 | 6 | 22 |
| [src/main/java/vjvm/runtime/classdata/constant/InterfaceMethodrefConstant.java](/src/main/java/vjvm/runtime/classdata/constant/InterfaceMethodrefConstant.java) | Java | 28 | 0 | 7 | 35 |
| [src/main/java/vjvm/runtime/classdata/constant/LongConstant.java](/src/main/java/vjvm/runtime/classdata/constant/LongConstant.java) | Java | 16 | 0 | 6 | 22 |
| [src/main/java/vjvm/runtime/classdata/constant/MethodrefConstant.java](/src/main/java/vjvm/runtime/classdata/constant/MethodrefConstant.java) | Java | 28 | 0 | 7 | 35 |
| [src/main/java/vjvm/runtime/classdata/constant/NameAndTypeConstant.java](/src/main/java/vjvm/runtime/classdata/constant/NameAndTypeConstant.java) | Java | 39 | 0 | 9 | 48 |
| [src/main/java/vjvm/runtime/classdata/constant/StringConstant.java](/src/main/java/vjvm/runtime/classdata/constant/StringConstant.java) | Java | 22 | 0 | 7 | 29 |
| [src/main/java/vjvm/runtime/classdata/constant/UTF8Constant.java](/src/main/java/vjvm/runtime/classdata/constant/UTF8Constant.java) | Java | 17 | 0 | 6 | 23 |
| [src/main/java/vjvm/runtime/classdata/constant/UnknownConstant.java](/src/main/java/vjvm/runtime/classdata/constant/UnknownConstant.java) | Java | 18 | 0 | 7 | 25 |
| [src/main/java/vjvm/utils/InputUtils.java](/src/main/java/vjvm/utils/InputUtils.java) | Java | 33 | 3 | 10 | 46 |
| [src/main/java/vjvm/utils/UnimplementedError.java](/src/main/java/vjvm/utils/UnimplementedError.java) | Java | 8 | 0 | 3 | 11 |
| [src/main/java/vjvm/utils/UnimplementedInstructionError.java](/src/main/java/vjvm/utils/UnimplementedInstructionError.java) | Java | 6 | 0 | 2 | 8 |
| [src/main/java/vjvm/vm/Main.java](/src/main/java/vjvm/vm/Main.java) | Java | 117 | 6 | 28 | 151 |
| [src/main/java/vjvm/vm/VMContext.java](/src/main/java/vjvm/vm/VMContext.java) | Java | 51 | 1 | 13 | 65 |
| [src/test/java/lab0/Dummy.java](/src/test/java/lab0/Dummy.java) | Java | 9 | 0 | 4 | 13 |
| [src/test/java/lab1/DumpClasses.java](/src/test/java/lab1/DumpClasses.java) | Java | 36 | 1 | 13 | 50 |
| [src/test/java/lab1/FindClasses.java](/src/test/java/lab1/FindClasses.java) | Java | 36 | 1 | 15 | 52 |
| [src/test/java/lab1/Utils.java](/src/test/java/lab1/Utils.java) | Java | 116 | 1 | 25 | 142 |
| [src/test/java/lab2/RunClassesTest.java](/src/test/java/lab2/RunClassesTest.java) | Java | 48 | 0 | 13 | 61 |
| [src/test/java/lab2/TestUtils.java](/src/test/java/lab2/TestUtils.java) | Java | 46 | 0 | 13 | 59 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)