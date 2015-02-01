/*
 * Copyright (c) 2011-2015 Pivotal Software, Inc.
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *         http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package reactor.bus.registry;

import reactor.fn.Consumer;

/**
 * Created by jbrisbin on 1/27/15.
 */
public abstract class Registries {

	private static boolean GS_COLLECTIONS_AVAILABLE = false;

	static {
		try {
			GS_COLLECTIONS_AVAILABLE = (null != Class.forName("com.gs.collections.api.list.MutableList"));
		} catch (ClassNotFoundException e) {
		}
	}

	protected Registries() {
	}

	@SuppressWarnings("unchecked")
	public static <T> Registry<T> create() {
		return create(true, true, null);
	}

	public static <T> Registry<T> create(boolean useCache, boolean cacheNotFound, Consumer<Object> onNotFound) {
		if (GS_COLLECTIONS_AVAILABLE) {
			return new reactor.bus.registry.CachingRegistry<T>(useCache, cacheNotFound, onNotFound);
		} else {
			return new reactor.bus.registry.SimpleCachingRegistry<T>(useCache, cacheNotFound, onNotFound);
		}
	}

}
