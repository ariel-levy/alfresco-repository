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
package org.alfresco.repo.search.impl.querymodel.impl.lucene.functions;

import java.util.Map;
import java.util.Set;

import org.alfresco.repo.search.impl.lucene.LuceneQueryParser;
import org.alfresco.repo.search.impl.querymodel.Argument;
import org.alfresco.repo.search.impl.querymodel.FunctionEvaluationContext;
import org.alfresco.repo.search.impl.querymodel.PropertyArgument;
import org.alfresco.repo.search.impl.querymodel.impl.functions.FTSFuzzyTerm;
import org.alfresco.repo.search.impl.querymodel.impl.lucene.LuceneQueryBuilderComponent;
import org.alfresco.repo.search.impl.querymodel.impl.lucene.LuceneQueryBuilderContext;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;

/**
 * Fuzzy matching
 * @author andyh
 *
 */
public class LuceneFTSFuzzyTerm extends FTSFuzzyTerm implements LuceneQueryBuilderComponent
{

    /**
     * 
     */
    public LuceneFTSFuzzyTerm()
    {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.alfresco.repo.search.impl.querymodel.impl.lucene.LuceneQueryBuilderComponent#addComponent(org.apache.lucene.search.BooleanQuery,
     *      org.apache.lucene.search.BooleanQuery, org.alfresco.service.cmr.dictionary.DictionaryService,
     *      java.lang.String)
     */
    public Query addComponent(Set<String> selectors, Map<String, Argument> functionArgs, LuceneQueryBuilderContext luceneContext, FunctionEvaluationContext functionContext)
            throws ParseException
    {
        LuceneQueryParser lqp = luceneContext.getLuceneQueryParser();
        Argument argument = functionArgs.get(ARG_TERM);
        String term = (String) argument.getValue(functionContext);
        argument = functionArgs.get(ARG_MIN_SIMILARITY);
        Float minSimilarity = (Float) argument.getValue(functionContext);

        PropertyArgument propArg = (PropertyArgument) functionArgs.get(ARG_PROPERTY);
        Query query;
        if (propArg != null)
        {
            String prop = propArg.getPropertyName();
            query = lqp.getFuzzyQuery(functionContext.getLuceneFieldName(prop), term, minSimilarity);
        }
        else
        {
            query = lqp.getFuzzyQuery(lqp.getField(), term, minSimilarity);
            
        }
        return query;
    }


}
