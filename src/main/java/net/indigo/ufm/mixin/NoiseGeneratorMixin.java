package net.indigo.ufm.mixin;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseGenerator;
import net.minecraft.world.gen.NoiseGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;

@Mixin(NoiseGenerator.class)
public class NoiseGeneratorMixin {
    @Shadow
    private int field_111;
    @Shadow
    private PerlinNoiseGenerator[] field_110;
    /**
     * @author Indigo227
     * @reason Removal of the Farlands Patch
     */
    @Overwrite()
    public double[] method_122(double[] ds, int i, int j, int k, int l, int m, int n, double d, double e, double f) {
        if (ds == null) {
            ds = new double[l * m * n];
        } else {
            Arrays.fill(ds, 0.0);
        }

        double var27 = 1.0;

        for (int var16 = 0; var16 < this.field_111; var16++) {
            double var17 = i * var27 * d;
            double var19 = j * var27 * e;
            double var21 = k * var27 * f;
            long var23 = MathHelper.lfloor(var17);
            long var25 = MathHelper.lfloor(var21);
            var17 -= var23;
            var21 -= var25;
            var17 += var23;
            var21 += var25;
            this.field_110[var16].method_119(ds, var17, var19, var21, l, m, n, d * var27, e * var27, f * var27, var27);
            var27 /= 2.0;
        }

        return ds;
    }
}