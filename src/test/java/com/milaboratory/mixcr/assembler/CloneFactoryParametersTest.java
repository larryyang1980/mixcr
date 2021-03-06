/*
 * Copyright (c) 2014-2015, Bolotin Dmitry, Chudakov Dmitry, Shugay Mikhail
 * (here and after addressed as Inventors)
 * All Rights Reserved
 *
 * Permission to use, copy, modify and distribute any part of this program for
 * educational, research and non-profit purposes, by non-profit institutions
 * only, without fee, and without a written agreement is hereby granted,
 * provided that the above copyright notice, this paragraph and the following
 * three paragraphs appear in all copies.
 *
 * Those desiring to incorporate this work into commercial products or use for
 * commercial purposes should contact the Inventors using one of the following
 * email addresses: chudakovdm@mail.ru, chudakovdm@gmail.com
 *
 * IN NO EVENT SHALL THE INVENTORS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE, EVEN IF THE INVENTORS HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE SOFTWARE PROVIDED HEREIN IS ON AN "AS IS" BASIS, AND THE INVENTORS HAS
 * NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
 * MODIFICATIONS. THE INVENTORS MAKES NO REPRESENTATIONS AND EXTENDS NO
 * WARRANTIES OF ANY KIND, EITHER IMPLIED OR EXPRESS, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY OR FITNESS FOR A
 * PARTICULAR PURPOSE, OR THAT THE USE OF THE SOFTWARE WILL NOT INFRINGE ANY
 * PATENT, TRADEMARK OR OTHER RIGHTS.
 */
package com.milaboratory.mixcr.assembler;

import com.milaboratory.core.alignment.AffineGapAlignmentScoring;
import com.milaboratory.core.alignment.BandedAlignerParameters;
import com.milaboratory.core.alignment.LinearGapAlignmentScoring;
import com.milaboratory.mixcr.vdjaligners.DAlignerParameters;
import com.milaboratory.util.GlobalObjectMappers;
import io.repseq.core.GeneFeature;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CloneFactoryParametersTest {
    @Test
    public void test1() throws Exception {
        CloneFactoryParameters paramentrs = new CloneFactoryParameters(
                new VJCClonalAlignerParameters(0.3f,
                        LinearGapAlignmentScoring.getNucleotideBLASTScoring(), 3),
                new VJCClonalAlignerParameters(0.4f,
                        LinearGapAlignmentScoring.getNucleotideBLASTScoring(), 5),
                new VJCClonalAlignerParameters(0.2f,
                        LinearGapAlignmentScoring.getNucleotideBLASTScoring(), 9),
                new DClonalAlignerParameters(0.85f, 30.0f, 3, AffineGapAlignmentScoring.getNucleotideBLASTScoring())
        );
        String str = GlobalObjectMappers.PRETTY.writeValueAsString(paramentrs);
        //System.out.println(str);
        CloneFactoryParameters deser = GlobalObjectMappers.PRETTY.readValue(str, CloneFactoryParameters.class);
        assertEquals(paramentrs, deser);
        CloneFactoryParameters clone = deser.clone();
        assertEquals(paramentrs, clone);
        deser.getVParameters().setRelativeMinScore(0.34f);
        assertFalse(clone.equals(deser));
    }

    @Test
    public void test2() throws Exception {
        CloneFactoryParameters paramentrs = new CloneFactoryParameters(
                new VJCClonalAlignerParameters(0.3f,
                        LinearGapAlignmentScoring.getNucleotideBLASTScoring(), 3),
                new VJCClonalAlignerParameters(0.4f,
                        LinearGapAlignmentScoring.getNucleotideBLASTScoring(), 5),
                null, new DClonalAlignerParameters(0.85f, 30.0f, 3, AffineGapAlignmentScoring.getNucleotideBLASTScoring())
        );
        String str = GlobalObjectMappers.PRETTY.writeValueAsString(paramentrs);
        //System.out.println(str);
        CloneFactoryParameters deser = GlobalObjectMappers.PRETTY.readValue(str, CloneFactoryParameters.class);
        assertEquals(paramentrs, deser);
        CloneFactoryParameters clone = deser.clone();
        assertEquals(paramentrs, clone);
        deser.getVParameters().setRelativeMinScore(0.34f);
        assertFalse(clone.equals(deser));
    }
}