/*
 * Copyright (c) 2011-2013 GoPivotal, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reactor.rx.action;

import org.reactivestreams.Publisher;
import reactor.event.dispatch.Dispatcher;

import java.util.List;

/**
 * @author Stephane Maldini
 * @since 2.0
 */
final public class MergeAction<O> extends FanInAction<O, O> {

	public MergeAction(Dispatcher dispatcher) {
		super(dispatcher);
	}

	public MergeAction(Dispatcher dispatcher, List<? extends Publisher<? extends O>> composables) {
		super(dispatcher, composables);
	}

	@Override
	protected void doNext(O ev) {
		broadcastNext(ev);
	}
}
