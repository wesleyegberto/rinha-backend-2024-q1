package com.github.wesleyegberto.apitransacoes;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.data.web.config.SpringDataJacksonConfiguration.PageModule;
import org.springframework.lang.Nullable;

/**
 * Unpaged será adicionado no 3.2.3.
 */
public class WebRuntimeHints implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {

		hints.reflection().registerType(TypeReference.of("org.springframework.data.domain.Unpaged"), hint -> {
			hint.onReachableType(PageModule.class);
		});
	}
}
