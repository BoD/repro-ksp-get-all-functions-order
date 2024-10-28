package org.example.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration

class MySymbolProcessor(private val logger: KSPLogger) : SymbolProcessor {
  override fun process(resolver: Resolver): List<KSAnnotated> {
    val classDeclarations = resolver.getSymbolsWithAnnotation("org.example.app.MyAnnotation").filterIsInstance<KSClassDeclaration>()
    //val functions = classDeclarations.first().getAllFunctions().toList()
    val functions = @OptIn(KspExperimental::class) resolver.getDeclarationsInSourceOrder(classDeclarations.first())
      .filterIsInstance<KSFunctionDeclaration>().toList()
    logger.warn("all functions:" + functions.map { it.simpleName.asString() })
    return emptyList()
  }
}

class MySymbolProcessorProvider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    return MySymbolProcessor(environment.logger)
  }
}
