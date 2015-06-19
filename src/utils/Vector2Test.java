package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.google.common.collect.Sets;

public class Vector2Test
{

    private static Vector2 newVector2()
    {
        final Random rGen = ThreadLocalRandom.current();
        final int x = rGen.nextInt();
        final int y = rGen.nextInt();
        return new Vector2(x, y);
    }

    @Test
    public void differentHashCodes()
    {
        final int numVectors = 200000; // arbitrary
        final Set<Integer> uniqueHashCodes = Sets.newHashSetWithExpectedSize(numVectors);
        for(int i = 0; i < numVectors; ++i)
        {
            final Vector2 vector = newVector2();
            uniqueHashCodes.add(vector.hashCode());
        }

        /*
         * Assume that at least 50% of our generated vectors were more or less
         * unique
         */
        assertTrue(uniqueHashCodes.size() >= (numVectors / 2));
    }

    @Test
    public void exhaustiveCopyConstruction()
    {
        final int numVectors = 200000; // arbitrary
        for(int i = 0; i < numVectors; ++i)
        {
            final Vector2 generatedVector2 = newVector2();
            final Vector2 copy = new Vector2(generatedVector2);
            /* Exhaustively check for equality */
            assertEquals(generatedVector2, generatedVector2);
            assertEquals(generatedVector2, copy);
            assertEquals(copy, generatedVector2);
            assertEquals(copy, copy);
            assertEquals(generatedVector2.getX(), copy.getX());
            assertEquals(generatedVector2.getY(), copy.getY());
            assertEquals(generatedVector2.hashCode(), copy.hashCode());
            assertNotSame(generatedVector2, copy);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCopy()
    {
        new Vector2((Vector2) null);
    }

}
