/*******************************************************************************
 * Copyright (c) 2012-2013 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *   * Michael Steindorfer - Michael.Steindorfer@cwi.nl - CWI  
 *******************************************************************************/
package org.eclipse.imp.pdb.facts.impl.persistent.clojure;

import java.io.IOException;
import java.io.StringWriter;

import org.eclipse.imp.pdb.facts.IAnnotatable;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.exceptions.IllegalOperationException;
import org.eclipse.imp.pdb.facts.io.StandardTextWriter;
import org.eclipse.imp.pdb.facts.type.Type;

abstract class Value implements IValue {

	protected final Type type;
	
	protected Value(Type type) {
		this.type = type;
	}
	
	@Override
	public Type getType() {
		return type;
	}

	@Override
	public boolean isEqual(IValue that) {
		return this.equals(that);
	}

	@Override
	public boolean isAnnotatable() {
		return false;
	}

	@Override
	public IAnnotatable<? extends IValue> asAnnotatable() {
		throw new IllegalOperationException(
				"Cannot be viewed as annotatable.", getType());
	}	
	
    @Override
    public final String toString() {
    	try {
    		StringWriter stream = new StringWriter();
    		new StandardTextWriter().write(this, stream);
			return stream.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
    }
	
}
