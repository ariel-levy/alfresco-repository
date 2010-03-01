/*
 * Copyright (C) 2005-2010 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.repo.content.filestore;

import java.io.File;

import org.alfresco.repo.content.AbstractWritableContentStoreTest;
import org.alfresco.repo.content.ContentStore;
import org.alfresco.util.TempFileProvider;

/**
 * Tests the file-based store when random access is not allowed, i.e. has to be spoofed.
 * 
 * @see org.alfresco.repo.content.filestore.FileContentStore
 * 
 * @since 2.1
 * @author Derek Hulley
 */
public class NoRandomAccessFileContentStoreTest extends AbstractWritableContentStoreTest
{
    private FileContentStore store;
    
    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        
        // create a store that uses a subdirectory of the temp directory
        File tempDir = TempFileProvider.getTempDir();
        store = new FileContentStore(applicationEventPublisher, tempDir.getAbsolutePath() + File.separatorChar
                + getName());
        // disallow random access
        store.setAllowRandomAccess(false);
    }
    
    @Override
    protected ContentStore getStore()
    {
        return store;
    }
}
