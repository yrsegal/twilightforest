package twilightforest.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTFSlimeProjectile extends EntityThrowable {

	public EntityTFSlimeProjectile(World par1World) {
		super(par1World);
	}

	public EntityTFSlimeProjectile(World par1World, EntityLivingBase par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
        makeTrail();
	}

	@Override
    protected float getGravityVelocity()
    {
		return 0.006F;
    }

	public void makeTrail() {
		for (int i = 0; i < 2; i++) {
			double dx = posX + 0.5 * (rand.nextDouble() - rand.nextDouble()); 
			double dy = posY + 0.5 * (rand.nextDouble() - rand.nextDouble()); 
			double dz = posZ + 0.5 * (rand.nextDouble() - rand.nextDouble()); 
			world.spawnParticle(EnumParticleTypes.SLIME, dx, dy, dz, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        setBeenAttacked();
		pop();
        return true;
     }

	@Override
	protected void onImpact(RayTraceResult par1MovingObjectPosition) {
		// only damage living things
		if (par1MovingObjectPosition.entityHit != null && par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
		{
			if (par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 8))
			{
				// damage armor?
				//TODO:
			}
		}

		pop();

	}

	private void pop() {
		for (int i = 0; i < 8; ++i)
		{
			this.world.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, rand.nextGaussian() * 0.05D, rand.nextDouble() * 0.2D, rand.nextGaussian() * 0.05D);
		}
		
		// noise
		this.world.playSoundAtEntity(this, "mob.slime.big", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));


		if (!this.world.isRemote)
		{
			this.setDead();
		}
	}


}
